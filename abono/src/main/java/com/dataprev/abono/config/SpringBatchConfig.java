package com.dataprev.abono.config;

import com.dataprev.abono.entities.Pagamento;
import com.dataprev.abono.mappers.PagamentoDatabaseRowMapper;
import com.dataprev.abono.processors.PagamentoProcessor;
import com.dataprev.abono.repositories.PagamentoRepository;
import jakarta.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class SpringBatchConfig {

    private PagamentoRepository pagamentoRepository;

    private JobRepository jobRepository;
    private PlatformTransactionManager platformTransactionManager;
    private DataSource dataSource;

    //private Resource outputResource = (Resource) new FileSystemResource("src/main/resources/users_output.csv");

    @Autowired
    public SpringBatchConfig(PagamentoRepository pagamentoRepository, JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, DataSource dataSource) {
        this.pagamentoRepository = pagamentoRepository;
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
        this.dataSource = dataSource;
    }

    @Bean
    public JsonItemReader<Pagamento> reader() {
        return new JsonItemReaderBuilder<Pagamento>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Pagamento.class))
                .resource(new ClassPathResource("pagamento.json"))
                .name("pagamentoJsonItemReader")
                .build();
    }

    @Bean
    public ItemStreamReader<Pagamento> pagamentoReader() {
        JdbcCursorItemReader<Pagamento> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT * FROM tb_pagamento INNER JOIN tb_trabalhador ON tb_pagamento.fk_trabalhador_id = tb_trabalhador.trabalhador_id INNER JOIN tb_banco ON tb_pagamento.fk_banco_id = tb_banco.banco_id");
        reader.setRowMapper(new PagamentoDatabaseRowMapper());
        return reader;
    }

    @Bean
    public PagamentoProcessor processor() {
        return new PagamentoProcessor();
    }

    @Bean
    public RepositoryItemWriter<Pagamento> writer() {
        RepositoryItemWriter<Pagamento> writer = new RepositoryItemWriter<>();
        writer.setRepository(pagamentoRepository);
        writer.setMethodName("save");

        return writer;
    }

    @Bean
    public ItemWriter<Pagamento> pagamentoWriter() {
        FlatFileItemWriter<Pagamento> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("src/main/resources/pagamento.csv"));
        writer.setLineAggregator(new DelimitedLineAggregator<Pagamento>() {
            {
                setFieldExtractor(new BeanWrapperFieldExtractor<Pagamento>() {
                    {
                        setNames(new String[]{"pagamento_id", "codigo_pagamento", "exercicio_financeiro", "ano_base", "numero_parcela", "valor_pagamento", "meses_trabalhados", "data_inicial_pagamento", "data_final_pagamento", "numero_sentence","banco_id", "banco", "agencia", "digito_verificador", "tipo_conta", "conta", "indicador_pagamento", "trabalhador_id", "nome", "nome_mae", "data_nascimento", "pis_pasep"});
                    }
                });
            }
        });
        writer.setShouldDeleteIfExists(true);
        return writer;
    }

    @Bean
    public Step importStep() {
        return new StepBuilder("jsonImport", jobRepository).
                <Pagamento, Pagamento>chunk(10, platformTransactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Step exportStep() {
        return new StepBuilder("csvExport", jobRepository)
                .<Pagamento, Pagamento>chunk(10, platformTransactionManager)
                .reader(pagamentoReader())
                .processor(processor())
                .writer(pagamentoWriter())
                .build();
    }

    @Bean
    public Job runJob() {
        return new JobBuilder("importPagamento", jobRepository)
                .start(importStep())
                .next(exportStep())
                .build();
    }
}

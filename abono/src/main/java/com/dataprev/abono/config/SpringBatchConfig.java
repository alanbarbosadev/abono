package com.dataprev.abono.config;

import com.dataprev.abono.dtos.PagamentoReportDto;
import com.dataprev.abono.entities.Pagamento;
import com.dataprev.abono.mappers.PagamentoDatabaseRowMapper;
import com.dataprev.abono.processors.PagamentoProcessor;
import com.dataprev.abono.repositories.PagamentoRepository;
import com.dataprev.abono.writers.ReportWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class SpringBatchConfig {
    private PagamentoRepository pagamentoRepository;
    private JobRepository jobRepository;
    private PlatformTransactionManager platformTransactionManager;
    private DataSource dataSource;

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
    public ItemWriter<PagamentoReportDto> pagamentoWriter() {
        ReportWriter<PagamentoReportDto> writer = new ReportWriter<PagamentoReportDto>();
        writer.setResource(new FileSystemResource("src/main/resources/pagamento.csv"));
        writer.setLineAggregator(new DelimitedLineAggregator<PagamentoReportDto>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<PagamentoReportDto>() {
                    {
//                        setNames(new String[]{"id", "codigoPagamento", "exercicioFinanceiro", "anoBase", "numeroParcela", "valorPagamento",
//                                "mesesTrabalhados", "dataInicialPagamento", "dataFinalPagamento", "numeroSentenca","banco.bancoId", "banco.banco",
//                                "banco.agencia", "banco.digitoVerificador", "banco.tipoConta", "banco.conta", "banco.indicadorPagamento",
//                                "trabalhador.cpf", "trabalhador.nome", "trabalhador.nomeMae",
//                                "trabalhador.nascimento", "trabalhador.pisPasep"});

                        setNames(new String[]{"identificacaoRegistro", "codigoPagamento", "exercicioFinanceiro", "anoBase", "pisPasep", "nome",  "nascimento",
                                "cpf", "nomeMae", "numeroParcela", "valorPagamento","mesesTrabalhados","dataInicialPagamento", "dataFinalPagamento", "numeroSentenca","banco",
                                "agencia", "digitoVerificador", "tipoConta", "conta", "indicadorPagamento", "zeros"});
                    }
                });
            }
        });
        //writer.setHeaderCallback();

        //writer.setFooterCallback(( x -> x.write("Contagem: " + writer.getCount() + "   Valor total: " + writer.getTotalValue())));
        writer.setShouldDeleteIfExists(true);
        return writer;
    }

    @Bean
    public Step importStep() {
        return new StepBuilder("jsonImport", jobRepository).
                <Pagamento, Pagamento>chunk(10, platformTransactionManager)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public Step exportStep() {
        return new StepBuilder("csvExport", jobRepository)
                .<Pagamento, PagamentoReportDto>chunk(10, platformTransactionManager)
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

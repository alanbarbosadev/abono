package com.dataprev.abono.controllers;

import com.dataprev.abono.entities.Pagamento;
import com.dataprev.abono.repositories.PagamentoRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/pagamento")
public class PagamentoController {

    private PagamentoRepository pagamentoRepository;
    private JobLauncher jobLauncher;
    private Job job;

    @Autowired
    public PagamentoController(PagamentoRepository pagamentoRepository, JobLauncher jobLauncher, Job job) {
        this.pagamentoRepository = pagamentoRepository;
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @PostMapping(value = "test")
    public List<Pagamento> criarPagamento(@RequestBody List<Pagamento> pagamentos) {
        List<Pagamento> pagamentosTest = new ArrayList<>();

        for(Pagamento pagamento : pagamentos) {
            pagamento = pagamentoRepository.save(pagamento);
            pagamentosTest.add(pagamento);
        };
        return pagamentosTest;
    }

    @PostMapping
    public void importPagamentoToDatabaseJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }

    }
}

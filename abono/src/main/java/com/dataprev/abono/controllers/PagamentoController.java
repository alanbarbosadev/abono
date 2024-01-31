package com.dataprev.abono.controllers;

import com.dataprev.abono.entities.Pagamento;
import com.dataprev.abono.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @PostMapping
    public Pagamento criarPagamento(Pagamento pagamento) {
        var pagamentoTest = new Pagamento();
        pagamentoTest.setMesesTrabalhados(11);
        pagamentoTest = pagamentoRepository.save(pagamentoTest);

        return pagamentoTest;
    }
}

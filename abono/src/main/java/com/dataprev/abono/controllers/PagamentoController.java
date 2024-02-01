package com.dataprev.abono.controllers;

import com.dataprev.abono.entities.Banco;
import com.dataprev.abono.entities.Pagamento;
import com.dataprev.abono.repositories.BancoRepository;
import com.dataprev.abono.repositories.PagamentoRepository;
import com.dataprev.abono.repositories.TrabalhadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private BancoRepository bancoRepository;
    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    @PostMapping
    public Pagamento criarPagamento(Pagamento pagamento) {
        var banco = new Banco();
        banco.setNome(pagamento.getBanco().getNome());
        banco.setAgencia(pagamento.getBanco().getAgencia());
        banco.setConta(pagamento.getBanco().getConta());
        banco.setDigitorVerificador(pagamento.getBanco().getDigitorVerificador());
        banco.setTipoConta(pagamento.getBanco().getTipoConta());

        banco = bancoRepository.save(banco);

        var pag = new Pagamento();
        pag.setCodigoPagamento(pagamento.getCodigoPagamento());
        pag.setValorPagamento(pagamento.getValorPagamento());
        pag.setMesesTrabalhados(pagamento.getMesesTrabalhados());
        pag.setBanco(banco);

        pag = pagamentoRepository.save(pag);


        return pag;
    }
}

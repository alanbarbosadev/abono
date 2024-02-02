package com.dataprev.abono.processors;

import com.dataprev.abono.dtos.PagamentoReportDto;
import com.dataprev.abono.entities.Pagamento;
import org.springframework.batch.item.ItemProcessor;

import java.text.SimpleDateFormat;

public class PagamentoProcessor implements ItemProcessor<Pagamento, PagamentoReportDto> {
    @Override
    public PagamentoReportDto process(Pagamento pagamento) throws Exception {
        return mapPagamentoToPagamentoReportDto(pagamento);
    }

    private PagamentoReportDto mapPagamentoToPagamentoReportDto(Pagamento pagamento) {

        var pagamentoReportDto = new PagamentoReportDto();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

         pagamentoReportDto.setIdentificacaoRegistro("21");
         pagamentoReportDto.setCodigoPagamento(pagamento.getCodigoPagamento());
         pagamentoReportDto.setExercicioFinanceiro("01012022");
         pagamentoReportDto.setAnoBase("2022");
         pagamentoReportDto.setNumeroParcela("01");
         pagamentoReportDto.setValorPagamento(pagamento.getValorPagamento());
         pagamentoReportDto.setMesesTrabalhados(pagamento.getMesesTrabalhados());
         pagamentoReportDto.setDataFinalPagamento("05022024");
         pagamentoReportDto.setDataFinalPagamento("31122024");
         pagamentoReportDto.setNumeroSentenca("0000000-00.0000.000.0000");
         pagamentoReportDto.setBanco(pagamento.getBanco().getBanco());
         pagamentoReportDto.setAgencia(pagamento.getBanco().getAgencia());
         pagamentoReportDto.setDigitoVerificador(pagamento.getBanco().getDigitoVerificador());
         pagamentoReportDto.setTipoConta(pagamento.getBanco().getTipoConta());
         pagamentoReportDto.setConta(pagamento.getBanco().getConta());
         pagamentoReportDto.setIndicadorPagamento(pagamento.getBanco().getIndicadorPagamento());
         pagamentoReportDto.setCpf(pagamento.getTrabalhador().getCpf());
         pagamentoReportDto.setNome(pagamento.getTrabalhador().getNome());
         pagamentoReportDto.setNomeMae(pagamento.getTrabalhador().getNomeMae());
         pagamentoReportDto.setNascimento(dateFormat.format(pagamento.getTrabalhador().getNascimento()));
         pagamentoReportDto.setPisPasep(pagamento.getTrabalhador().getPisPasep());
         pagamentoReportDto.setZeros("0000000");

         return pagamentoReportDto;

    }
}

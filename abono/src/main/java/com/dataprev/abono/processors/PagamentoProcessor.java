package com.dataprev.abono.processors;

import com.dataprev.abono.dtos.PagamentoReportDto;
import com.dataprev.abono.entities.Pagamento;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
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
         pagamentoReportDto.setCodigoPagamento(formatCodigoPagamento(pagamento.getCodigoPagamento()));
         pagamentoReportDto.setExercicioFinanceiro("01012022");
         pagamentoReportDto.setAnoBase("2022");
         pagamentoReportDto.setNumeroParcela("01");
         pagamentoReportDto.setValorPagamento(formatValorPagamento(pagamento.getValorPagamento()));
         pagamentoReportDto.setMesesTrabalhados(formatMesesTrabalhados(pagamento.getMesesTrabalhados()));
         pagamentoReportDto.setDataInicialPagamento("05022024");
         pagamentoReportDto.setDataFinalPagamento("31122024");
         pagamentoReportDto.setNumeroSentenca("00000000000000000000");
         pagamentoReportDto.setBanco(formatBanco(pagamento.getBanco().getBanco()));
         pagamentoReportDto.setAgencia(formatAgencia(pagamento.getBanco().getAgencia()));
         pagamentoReportDto.setDigitoVerificador(formatDigitoVerificador(pagamento.getBanco().getDigitoVerificador()));
         pagamentoReportDto.setTipoConta(pagamento.getBanco().getTipoConta());
         pagamentoReportDto.setConta(formatConta(pagamento.getBanco().getConta()));
         pagamentoReportDto.setIndicadorPagamento(formatTipoConta(pagamento.getBanco().getIndicadorPagamento()));
         pagamentoReportDto.setCpf(formatCpf(pagamento.getTrabalhador().getCpf()));
         pagamentoReportDto.setNome(formatNome(pagamento.getTrabalhador().getNome()));
         pagamentoReportDto.setNomeMae(formatNome(pagamento.getTrabalhador().getNomeMae()));
         pagamentoReportDto.setNascimento(dateFormat.format(pagamento.getTrabalhador().getNascimento()));
         pagamentoReportDto.setPisPasep(formatPisPasep(pagamento.getTrabalhador().getPisPasep()));
         pagamentoReportDto.setZeros("0000000");

         return pagamentoReportDto;

    }

    private String formatCodigoPagamento(String codigoPagamento) {

        int charSize = 12;

        if(codigoPagamento.length() < charSize){

            var zeroCount = charSize - codigoPagamento.length();

            return StringUtils.leftPad(codigoPagamento,zeroCount,"0");
        }

        return codigoPagamento;
    }

    private String formatValorPagamento(BigDecimal valorPagamento) {

        int charSize = 9;

        var valorPagamentoStr = valorPagamento.toString();

        if(valorPagamentoStr.length() < charSize){

            var zeroCount = charSize - valorPagamentoStr.length();

            return StringUtils.leftPad(valorPagamentoStr,zeroCount,"0");
        }

        return valorPagamentoStr;
    }

    private String formatMesesTrabalhados(String mesesTrabalhados) {

        int charSize = 2;

        if(mesesTrabalhados.length() < charSize){

            var zeroCount = charSize - mesesTrabalhados.length();

            return StringUtils.leftPad(mesesTrabalhados,zeroCount,"0");
        }

        return mesesTrabalhados;
    }

    private String formatBanco(String banco) {

        int charSize = 4;

        if(banco.length() < charSize){

            var zeroCount = charSize - banco.length();

            return StringUtils.leftPad(banco,zeroCount,"0");
        }

        return banco;
    }

    private String formatAgencia(String agencia) {

        int charSize = 5;

        if(agencia.length() < charSize){

            var zeroCount = charSize - agencia.length();

            return StringUtils.leftPad(agencia,zeroCount,"0");
        }

        return agencia;
    }

    private String formatDigitoVerificador(String digitoVerificador) {

//        int charSize = 1;
//
//        if(digitoVerificador.length() < charSize){
//
//            int zeroCount = charSize - digitoVerificador.length();
//
//            return StringUtils.leftPad(digitoVerificador,zeroCount,"0");
//        }

        return digitoVerificador;
    }
    private String formatConta(String conta) {

        int charSize = 15;

        if(conta.length() < charSize){

            var zeroCount = charSize - conta.length();

            return StringUtils.leftPad(conta,zeroCount,"0");
        }

        return conta;
    }

    private String formatTipoConta(String tipoConta) {

        int charSize = 2;

        if(tipoConta.length() < charSize){

            var zeroCount = charSize - tipoConta.length();

            return StringUtils.leftPad(tipoConta,zeroCount,"0");
        }

        return tipoConta;
    }

    private String formatCpf(String cpf) {

        int charSize = 11;

        if(cpf.length() < charSize){

            var zeroCount = charSize - cpf.length();

            return StringUtils.leftPad(cpf,zeroCount,"0");
        }

        return cpf;
    }

    private String formatNome(String nome) {

        int charSize = 70;

        if(nome.length() < charSize){

            var spaceCount = charSize - nome.length();

            return StringUtils.leftPad(nome,spaceCount," ");
        }

        return nome;
    }

    private String formatPisPasep(String pispasep) {

        int charSize = 11;

        if(pispasep.length() < charSize){

            var zeroCount = charSize - pispasep.length();

            return StringUtils.leftPad(pispasep,zeroCount,"0");
        }

        return pispasep;
    }
}

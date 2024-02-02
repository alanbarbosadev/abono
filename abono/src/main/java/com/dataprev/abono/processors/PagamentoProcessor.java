package com.dataprev.abono.processors;

import com.dataprev.abono.dtos.PagamentoReportDto;
import com.dataprev.abono.entities.Pagamento;
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
         pagamentoReportDto.setTipoConta(formatTipoConta(pagamento.getBanco().getTipoConta()));
         pagamentoReportDto.setConta(formatConta(pagamento.getBanco().getConta()));
         pagamentoReportDto.setIndicadorPagamento(formatIndicador(pagamento.getBanco().getIndicadorPagamento()));
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

        if(codigoPagamento.length() < charSize) {

            int zeroCount = charSize - codigoPagamento.length();

            var str = new StringBuilder(codigoPagamento);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, '0');
            }

            return str.toString();
        }

        return codigoPagamento;
    }

    private String formatValorPagamento(BigDecimal valorPagamento) {

        int charSize = 9;

        String valorPagamentoStr = valorPagamento.toString();

        if(valorPagamentoStr.length() < charSize) {

            int zeroCount = charSize - valorPagamentoStr.length();

            var str = new StringBuilder(valorPagamentoStr);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, '0');
            }

            return str.toString();
        }

        return valorPagamentoStr;
    }

    private String formatMesesTrabalhados(String mesesTrabalhados) {

        int charSize = 2;

        if(mesesTrabalhados.length() < charSize) {

            int zeroCount = charSize - mesesTrabalhados.length();

            var str = new StringBuilder(mesesTrabalhados);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, '0');
            }

            return str.toString();
        }

        return mesesTrabalhados;
    }

    private String formatBanco(String banco) {

        int charSize = 4;

        if(banco.length() < charSize) {

            int zeroCount = charSize - banco.length();

            var str = new StringBuilder(banco);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, '0');
            }

            return str.toString();
        }

        return banco;
    }

    private String formatAgencia(String agencia) {

        int charSize = 5;

        if(agencia.length() < charSize) {

            int zeroCount = charSize - agencia.length();

            var str = new StringBuilder(agencia);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, '0');
            }

            return str.toString();
        }

        return agencia;
    }

    private String formatDigitoVerificador(String digitoVerificador) {

        int charSize = 1;

        if(digitoVerificador.length() < charSize) {

            int zeroCount = charSize - digitoVerificador.length();

            var str = new StringBuilder(digitoVerificador);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, '0');
            }

            return str.toString();
        }

        return digitoVerificador;
    }
    private String formatConta(String conta) {

        int charSize = 15;

        if(conta.length() < charSize) {

            int zeroCount = charSize - conta.length();

            var str = new StringBuilder(conta);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, '0');
            }

            return str.toString();
        }

        return conta;
    }

    private String formatTipoConta(String tipoConta) {

        int charSize = 2;

        if(tipoConta.length() < charSize) {

            int zeroCount = charSize - tipoConta.length();

            var str = new StringBuilder(tipoConta);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, '0');
            }

            return str.toString();
        }

        return tipoConta;
    }

    private String formatIndicador(String indicador) {

        int charSize = 1;

        if(indicador.length() < charSize) {

            int zeroCount = charSize - indicador.length();

            var str = new StringBuilder(indicador);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, '0');
            }

            return str.toString();
        }

        return indicador;
    }

    private String formatCpf(String cpf) {

        int charSize = 11;

        if(cpf.length() < charSize) {

            int zeroCount = charSize - cpf.length();

            var str = new StringBuilder(cpf);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, '0');
            }

            return str.toString();
        }

        return cpf;
    }

    private String formatNome(String nome) {

        int charSize = 70;

        if(nome.length() < charSize) {

            int zeroCount = charSize - nome.length();

            var str = new StringBuilder(nome);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, ' ');
            }

            return str.toString();
        }

        return nome;
    }

    private String formatPisPasep(String pispasep) {

        int charSize = 11;

        if(pispasep.length() < charSize) {

            int zeroCount = charSize - pispasep.length();

            var str = new StringBuilder(pispasep);

            for (int i = 0; i < zeroCount; i++) {
                str.insert(0, ' ');
            }

            return str.toString();
        }

        return pispasep;
    }
}

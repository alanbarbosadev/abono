package com.dataprev.abono.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="TB_PAGAMENTO")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long codigoPagamento;
    private String exercicioFinanceiro = "01012022";
    private String anoBase = "2022";
    private String numeroParcela = "01";
    private BigDecimal valorPagamento;
    private Integer mesesTrabalhados;
    private String dataInicialPagamento = "05022024";
    private String dataFinalPagamento = "31122024";
    private String numeroSentenca = "0000000-00.0000.000.0000";
    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL)
    private Banco banco;
    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL)
    private Trabalhador trabalhador;

    public Pagamento(Integer id, Long codigoPagamento, String exercicioFinanceiro, String anoBase, String numeroParcela, BigDecimal valorPagamento, Integer mesesTrabalhados, String dataInicialPagamento, String dataFinalPagamento, String numeroSentenca, Banco banco, Trabalhador trabalhador) {
        this.id = id;
        this.codigoPagamento = codigoPagamento;
        this.exercicioFinanceiro = exercicioFinanceiro;
        this.anoBase = anoBase;
        this.numeroParcela = numeroParcela;
        this.valorPagamento = valorPagamento;
        this.mesesTrabalhados = mesesTrabalhados;
        this.dataInicialPagamento = dataInicialPagamento;
        this.dataFinalPagamento = dataFinalPagamento;
        this.numeroSentenca = numeroSentenca;
        this.banco = banco;
        this.trabalhador = trabalhador;
    }

    public Pagamento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCodigoPagamento() {
        return codigoPagamento;
    }

    public void setCodigoPagamento(Long codigoPagamento) {
        this.codigoPagamento = codigoPagamento;
    }

    public String getExercicioFinanceiro() {
        return exercicioFinanceiro;
    }

    public void setExercicioFinanceiro(String exercicioFinanceiro) {
        this.exercicioFinanceiro = exercicioFinanceiro;
    }

    public String getAnoBase() {
        return anoBase;
    }

    public void setAnoBase(String anoBase) {
        this.anoBase = anoBase;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Integer getMesesTrabalhados() {
        return mesesTrabalhados;
    }

    public void setMesesTrabalhados(Integer mesesTrabalhados) {
        this.mesesTrabalhados = mesesTrabalhados;
    }

    public String getDataInicialPagamento() {
        return dataInicialPagamento;
    }

    public void setDataInicialPagamento(String dataInicialPagamento) {
        this.dataInicialPagamento = dataInicialPagamento;
    }

    public String getDataFinalPagamento() {
        return dataFinalPagamento;
    }

    public void setDataFinalPagamento(String dataFinalPagamento) {
        this.dataFinalPagamento = dataFinalPagamento;
    }

    public String getNumeroSentenca() {
        return numeroSentenca;
    }

    public void setNumeroSentenca(String numeroSentenca) {
        this.numeroSentenca = numeroSentenca;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Trabalhador getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(Trabalhador trabalhador) {
        this.trabalhador = trabalhador;
    }
}

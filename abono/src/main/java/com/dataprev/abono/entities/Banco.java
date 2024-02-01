package com.dataprev.abono.entities;

import jakarta.persistence.*;

@Entity
@Table(name="TB_BANCO")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="banco_id")
    private Long bancoId;
    @Column(name="nome")
    private String nome;
    @Column(name="agencia")
    private Long agencia;
    @Column(name="digitor_verificador")
    private Integer digitorVerificador;
    @Column(name="tipo_conta")
    private String tipoConta;
    @Column(name="conta")
    private Long conta;
    @Column(name="indicador_pagamento")
    private Integer indicadorPagamento;
    @OneToOne
    @JoinColumn(name="pagamento_id")
    private Pagamento pagamento;

    public Banco(Long id, String nome, Long agencia, Integer digitorVerificador, String tipoConta, Long conta, Integer indicadorPagamento, Pagamento pagamento) {
        this.bancoId = id;
        this.nome = nome;
        this.agencia = agencia;
        this.digitorVerificador = digitorVerificador;
        this.tipoConta = tipoConta;
        this.conta = conta;
        this.indicadorPagamento = indicadorPagamento;
        this.pagamento = pagamento;
    }

    public Banco() {
    }

    public Long getBancoId() {
        return bancoId;
    }

    public void setBancoId(Long id) {
        this.bancoId = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public Integer getDigitorVerificador() {
        return digitorVerificador;
    }

    public void setDigitorVerificador(Integer digitorVerificador) {
        this.digitorVerificador = digitorVerificador;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public Integer getIndicadorPagamento() {
        return indicadorPagamento;
    }

    public void setIndicadorPagamento(Integer indicadorPagamento) {
        this.indicadorPagamento = indicadorPagamento;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}

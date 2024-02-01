package com.dataprev.abono.entities;

import jakarta.persistence.*;

@Entity
@Table(name="TB_BANCO")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="banco_id")
    private Long bancoId;
    @Column(name="banco")
    private String banco;
    @Column(name="agencia")
    private Long agencia;
    @Column(name="digito_verificador")
    private Integer digitoVerificador;
    @Column(name="tipo_conta")
    private String tipoConta;
    @Column(name="conta")
    private Long conta;
    @Column(name="indicador_pagamento")
    private Integer indicadorPagamento;

    public Banco(Long id, String banco, Long agencia, Integer digitoVerificador, String tipoConta, Long conta, Integer indicadorPagamento) {
        this.bancoId = id;
        this.banco = banco;
        this.agencia = agencia;
        this.digitoVerificador = digitoVerificador;
        this.tipoConta = tipoConta;
        this.conta = conta;
        this.indicadorPagamento = indicadorPagamento;
    }

    public Banco() {
    }

    public Long getBancoId() {
        return bancoId;
    }

    public void setBancoId(Long id) {
        this.bancoId = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public Integer getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitorVerificador(Integer digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
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
}

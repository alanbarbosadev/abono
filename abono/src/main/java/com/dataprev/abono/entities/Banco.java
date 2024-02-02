package com.dataprev.abono.entities;

import jakarta.persistence.*;

@Entity
@Table(name="TB_BANCO")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="banco_id")
    private Integer bancoId;
    @Column(name="banco")
    private String banco;
    @Column(name="agencia")
    private String agencia;
    @Column(name="digito_verificador")
    private String digitoVerificador;
    @Column(name="tipo_conta")
    private String tipoConta;
    @Column(name="conta")
    private String conta;
    @Column(name="indicador_pagamento")
    private String indicadorPagamento;

    public Banco(Integer bancoId, String banco, String agencia, String digitoVerificador, String tipoConta, String conta, String indicadorPagamento) {
        this.bancoId = bancoId;
        this.banco = banco;
        this.agencia = agencia;
        this.digitoVerificador = digitoVerificador;
        this.tipoConta = tipoConta;
        this.conta = conta;
        this.indicadorPagamento = indicadorPagamento;
    }

    public Banco() {
    }

    public Integer getBancoId() {
        return bancoId;
    }

    public void setBancoId(Integer bancoId) {
        this.bancoId = bancoId;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getIndicadorPagamento() {
        return indicadorPagamento;
    }

    public void setIndicadorPagamento(String indicadorPagamento) {
        this.indicadorPagamento = indicadorPagamento;
    }
}

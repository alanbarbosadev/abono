package com.dataprev.abono.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TB_TRABALHADOR")
public class Trabalhador {

    @Id
    @Column(name="trabalhador_id")
    private Long cpf;
    @Column(name="nome")
    private String nome;
    @Column(name="nome_mae")
    private String nomeMae;
    @Column(name="data_nascimento")
    private Date nascimento;
    @Column(name="pis_pasep")
    private Long pisPasep;

    public Trabalhador(Long cpf, String nome, String nomeMae, Date nascimento, Long pisPasep) {
        this.cpf = cpf;
        this.nome = nome;
        this.nomeMae = nomeMae;
        this.nascimento = nascimento;
        this.pisPasep = pisPasep;
    }

    public Trabalhador() {
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Long getPisPasep() {
        return pisPasep;
    }

    public void setPisPasep(Long pisPasep) {
        this.pisPasep = pisPasep;
    }
}

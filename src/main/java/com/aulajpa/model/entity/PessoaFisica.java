package com.aulajpa.model.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class PessoaFisica extends Pessoa{
    @NotBlank(message = "CPF é Obrigatório")
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
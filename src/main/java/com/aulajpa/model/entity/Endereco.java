package com.aulajpa.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.beans.Encoder;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Endereco {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String cep;
    private String logradouro;

    private String complemento;
    private String bairro;

    @ManyToOne
    private Pessoa pessoa;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cidade cidade;

    @OneToMany(mappedBy = "endereco")
    private List<Venda> vendas = new ArrayList<>();

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}

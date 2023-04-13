package com.aulajpa.model.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
public class ItemVenda {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private double qtd;
    @OneToOne
    private Produto produto;
    @ManyToOne
    private Venda venda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public double total() {
        return qtd * produto.getValor();
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

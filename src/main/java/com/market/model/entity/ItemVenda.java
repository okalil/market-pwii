package com.market.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class ItemVenda {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Min( value = 1, message = "Quantidade não pode ser menor do que 1!")
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

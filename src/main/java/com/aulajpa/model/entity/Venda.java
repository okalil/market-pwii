package com.aulajpa.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Scope("session")
@Component
@Entity
public class Venda {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;

    @CreatedDate
    LocalDate data;

    @OneToMany(mappedBy = "venda")
    List<ItemVenda> itens;

    @ManyToOne
    Pessoa comprador;

    Venda() {
        this.data = LocalDate.now();
        this.itens = new ArrayList<>();
    };

    public double total() {
        double t = 0;
        for (ItemVenda item : itens) {
            t += item.total();
        }
        return t;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public Pessoa getComprador() {
        return comprador;
    }

    public void setComprador(Pessoa comprador) {
        this.comprador = comprador;
    }
}

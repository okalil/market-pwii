package com.aulajpa.model.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Venda {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @CreatedDate
    LocalDate data;

    @OneToMany(mappedBy = "venda")
    List<ItemVenda> itens;

    Venda() {
      this.data = LocalDate.now();
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
}

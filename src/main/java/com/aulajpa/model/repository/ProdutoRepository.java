package com.aulajpa.model.repository;

import com.aulajpa.model.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Produto> todos() {
        Query query = em.createQuery("from Produto", Produto.class);
        return query.getResultList();
    }

    public Produto buscarUm(int produtoId) {
      Produto produto = em.find(Produto.class, produtoId);
      return produto;
    };

    public void criar(Produto produto) {
        em.persist(produto);
    }
}

package com.aulajpa.model.repository;

import com.aulajpa.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Venda> todos() {
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }
}
package com.aulajpa.model.repository;

import com.aulajpa.model.entity.Endereco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class EnderecoRepository {
    @PersistenceContext
    private EntityManager em;

    public void criar(Endereco endereco) {
        em.persist(endereco);
    }
}

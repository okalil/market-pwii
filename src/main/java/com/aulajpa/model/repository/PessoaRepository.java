package com.aulajpa.model.repository;

import com.aulajpa.model.entity.PessoaFisica;
import com.aulajpa.model.entity.PessoaJuridica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaRepository {
    @PersistenceContext
    private EntityManager em;

    public void criar(PessoaFisica pessoa) {
        em.persist(pessoa);
    }

    public void criar(PessoaJuridica pessoa) {
        em.persist(pessoa);
    }
}

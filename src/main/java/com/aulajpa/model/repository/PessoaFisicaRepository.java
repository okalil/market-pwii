package com.aulajpa.model.repository;

import com.aulajpa.model.entity.Pessoa;
import com.aulajpa.model.entity.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaFisicaRepository {
    @PersistenceContext
    private EntityManager em;

    public void criar(PessoaFisica pessoa) {
        em.persist(pessoa);
    }

    public Pessoa buscarUm(Long pessoaId) {
        return em.find(Pessoa.class, pessoaId);
    }

    public List<Pessoa> todos() {
        Query query = em.createQuery("from Pessoa", Pessoa.class);
        return query.getResultList();
    }
}

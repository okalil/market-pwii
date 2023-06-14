package com.market.model.repository;

import com.market.model.entity.Pessoa;
import com.market.model.entity.PessoaJuridica;
import com.market.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaJuridicaRepository {
    @PersistenceContext
    private EntityManager em;

    public void criar(PessoaJuridica pessoa) {
        em.persist(pessoa);
        em.flush();
    }

    public Pessoa buscarUm(Long pessoaId) {
        return em.find(Pessoa.class, pessoaId);
    }

    public List<Pessoa> todos() {
        Query query = em.createQuery("from PessoaJuridica", Pessoa.class);
        return query.getResultList();
    }

    public List<Venda> todosPorNome(String nome) {
        Query query = em.createQuery("from PessoaJuridica p where lower(p.nome) like lower(:nome)");
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
}

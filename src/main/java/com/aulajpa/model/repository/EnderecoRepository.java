package com.aulajpa.model.repository;

import com.aulajpa.model.entity.Endereco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnderecoRepository {
    @PersistenceContext
    private EntityManager em;

    public void criar(Endereco endereco) {
        em.persist(endereco);
    }

    public List<Endereco> todos(Long pessoaId) {
        var query = em.createQuery("from Endereco", Endereco.class);
        return query.getResultList();
    };

    public List<Endereco> todosPorPessoa(Long pessoaId) {
        var query = em.createQuery("from Endereco e where e.pessoa.id = :id", Endereco.class);
        query.setParameter("id",pessoaId);
        return query.getResultList();
    };

    public Endereco buscarUm(int enderecoId) {
        return em.find(Endereco.class, enderecoId);
    };
}

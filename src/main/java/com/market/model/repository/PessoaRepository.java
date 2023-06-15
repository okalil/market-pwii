package com.market.model.repository;

import com.market.model.entity.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class PessoaRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Pessoa> findAll() {
        Query query = em.createQuery("from Pessoa", Pessoa.class);
        return query.getResultList();
    }

    public Pessoa findByUsuario(String usuario) {
        var query = em.createQuery("from Pessoa p where usuario.usuario = :usuario", Pessoa.class);
        query.setParameter("usuario", usuario);
        return query.getSingleResult();
    }
}

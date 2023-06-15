package com.market.model.repository;

import com.market.model.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {
    @PersistenceContext
    private EntityManager em;

    public Role findByName(String nome) {
        var query = em.createQuery("from Role where nome = :nome", Role.class);
        query.setParameter("nome", nome);
        Role role = query.getSingleResult();
        return role;
    }

}

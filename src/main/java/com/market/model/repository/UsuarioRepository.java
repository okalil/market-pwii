package com.market.model.repository;


import com.market.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    @PersistenceContext
    private EntityManager em;

    public Usuario usuario(String usuario) {
        try {
            String jpql = "from Usuario u where u.usuario = :usuario";
            var query = em.createQuery(jpql, Usuario.class);
            query.setParameter("usuario", usuario);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Usuario usuario) {
        em.persist(usuario);
    }
}
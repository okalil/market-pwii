package com.market.model.repository;

import com.market.model.entity.ItemVenda;
import com.market.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Venda> findAllByPessoaUsuario(String usuario) {
        var query = em.createQuery("from Venda v where v.comprador.usuario.usuario = :usuario");
        query.setParameter("usuario", usuario);
        return query.getResultList();
    }

    public List<Venda> findAllByPessoaUsuarioAndData(String usuario, LocalDate data) {
        Query query = em.createQuery("from Venda v where v.data = :data and v.compardor.usuario.usuario = :usuario");
        query.setParameter("usuario", usuario);
        query.setParameter("data", data);
        return query.getResultList();
    }

    public void criar(Venda venda) {
        em.persist(venda);
        for (ItemVenda itemVenda : venda.getItens()) {
            em.persist(itemVenda);
        }
    }

    public Venda buscarUm(int vendaId) {
        return em.find(Venda.class, vendaId);
    }
}
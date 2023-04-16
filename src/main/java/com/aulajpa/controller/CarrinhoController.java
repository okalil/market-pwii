package com.aulajpa.controller;

import com.aulajpa.model.entity.ItemVenda;
import com.aulajpa.model.entity.Pessoa;
import com.aulajpa.model.entity.Produto;
import com.aulajpa.model.entity.Venda;
import com.aulajpa.model.repository.PessoaRepository;
import com.aulajpa.model.repository.ProdutoRepository;
import com.aulajpa.model.repository.VendaRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Scope("request")
@Transactional
@Controller
@RequestMapping("carrinho")
public class CarrinhoController {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    Venda venda;

    @GetMapping
    public ModelAndView carrinho(ModelMap model) {
        model.addAttribute("venda", venda);
        model.addAttribute("pessoas", pessoaRepository.todos());
        return new ModelAndView("/carrinho", model);
    }

    @PostMapping
    public String finalizar(Long id, HttpSession session) {
        Pessoa pessoa = pessoaRepository.buscarUm(id);
        venda.setComprador(pessoa);
        vendaRepository.criar(venda);
        session.invalidate();
        return "redirect:/vendas";
    }

    @PostMapping("adicionar")
    public String adicionar(ItemVenda itemVenda) {
        List<ItemVenda> itens = venda.getItens();
        int produtoId = itemVenda.getProduto().getId();

        ItemVenda existingItem = itens.stream()
                .filter(item -> item.getProduto().getId() == produtoId)
                .findFirst()
                .orElse(null);
        if (existingItem != null) {
            existingItem.setQtd(existingItem.getQtd() + itemVenda.getQtd());
        } else {
            Produto produto = produtoRepository.buscarUm(produtoId);
            itemVenda.setProduto(produto);
            itemVenda.setVenda(venda);
            itens.add(itemVenda);
        }

        return "redirect:/";
    }

    @PostMapping("delete")
    public String deletar(int index) {
        venda.getItens().remove(index);
        return "redirect:/carrinho";
    }
}

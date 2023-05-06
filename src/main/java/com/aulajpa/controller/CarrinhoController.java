package com.aulajpa.controller;

import com.aulajpa.model.entity.*;
import com.aulajpa.model.repository.EnderecoRepository;
import com.aulajpa.model.repository.PessoaJuridicaRepository;
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
    PessoaJuridicaRepository pessoaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    Venda venda;

    @GetMapping
    public ModelAndView carrinho(ModelMap model) {
        model.addAttribute("venda", venda);
        model.addAttribute("pessoas", pessoaRepository.todos());

        return new ModelAndView("/carrinho", model);
    }

    @PostMapping
    public String continuar(Long id) {
        Pessoa pessoa = pessoaRepository.buscarUm(id);
        venda.setComprador(pessoa);

        return "redirect:/carrinho/finalizar";
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

    @GetMapping("finalizar")
    public String mostrarFinalizar(ModelMap model) {
        Pessoa comprador = venda.getComprador();

        if(comprador == null) return "redirect:/carrinho";

        model.addAttribute("venda", venda);
        model.addAttribute("enderecos", enderecoRepository.todosPorPessoa(comprador.getId()));

        return "/endereco";
    }

    @PostMapping("finalizar")
    public String finalizar(Endereco endereco, HttpSession session) {
        endereco = enderecoRepository.buscarUm(endereco.getId());

        vendaRepository.criar(venda);
        session.invalidate();

        return "redirect:/vendas";
    }
}

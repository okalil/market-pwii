package com.market.controller;

import com.market.model.entity.*;
import com.market.model.repository.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    EnderecoRepository enderecoRepository;

    @Autowired
    Venda venda;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public String carrinho(ModelMap model) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("authenticated", true);
            model.addAttribute("usuario", authentication.getName());
            List<Role> roles = (List<Role>) authentication.getAuthorities();
            model.addAttribute("isAdmin", roles.stream().filter(it -> "ADMIN".equals(it.getNome())).findFirst().orElse(null));
        } else {
            model.addAttribute("isAdmin", false);
        }
        model.addAttribute("venda", venda);
        model.addAttribute("pessoas", pessoaRepository.findAll());

        return "/carrinho";
    }

    @PostMapping
    public String continuar(ModelMap model) {

        if (venda.getItens().size() < 1) {
            model.addAttribute("erro", "Adicione pelo menos um item ao carrinho!");
            return carrinho(model);
        }

        return "redirect:/carrinho/finalizar";
    }

    @PostMapping("delete")
    public String deletar(int index) {
        venda.getItens().remove(index);
        return "redirect:/carrinho";
    }

    @GetMapping("finalizar")
    public String mostrarFinalizar(ModelMap model) {
        if (venda.getItens().size() < 1) {
            return "redirect:/carrinho";
        }

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("authenticated", true);
            model.addAttribute("usuario", authentication.getName());
            List<Role> roles = (List<Role>) authentication.getAuthorities();
            model.addAttribute("isAdmin", roles.stream().filter(it -> "ADMIN".equals(it.getNome())).findFirst().orElse(null));
        } else {
            model.addAttribute("isAdmin", false);
        }

        String username = authentication.getName();
        Pessoa comprador = pessoaRepository.findByUsuario(username);
        venda.setComprador(comprador);

        model.addAttribute("venda", venda);
        model.addAttribute("enderecos", enderecoRepository.todosPorPessoa(comprador.getId()));

        return "/endereco";
    }

    @PostMapping("finalizar")
    public String finalizar(Integer enderecoId, HttpSession session, ModelMap model) {
        if (venda.getItens().size() < 1) {
            model.addAttribute("erro", "Adicione pelo menos um item ao carrinho!");
            return carrinho(model);
        }

        if (enderecoId == null) {
            model.addAttribute("erro", "Selecione um endereço!");
            return mostrarFinalizar(model);
        }

        Endereco endereco = enderecoRepository.buscarUm(enderecoId);

        if (endereco == null) {
            model.addAttribute("erro", "Selecione um endereço válido!");
            return mostrarFinalizar(model);
        }

        venda.setEndereco(endereco);
        vendaRepository.criar(venda);
        session.removeAttribute("venda");

        return "redirect:/vendas";
    }
}

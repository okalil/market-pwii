package com.aulajpa.controller;

import com.aulajpa.model.entity.PessoaFisica;
import com.aulajpa.model.entity.PessoaJuridica;
import com.aulajpa.model.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping("pessoas")
public class PessoaController {

    @Autowired
    PessoaRepository repository;

    @GetMapping("/fisica/cadastrar")
    public String fisica() {
        return "/pessoas/pf-form";
    }

    @GetMapping("/juridica/cadastrar")
    public String juridica() {
        return "/pessoas/pj-form";
    }

    @PostMapping("/fisica/cadastrar")
    public String cadastrar(PessoaFisica pessoa) {
        repository.criar(pessoa);
        return "redirect:/vendas/list";
    }

    @PostMapping("/juridica/cadastrar")
    public String cadastrar(PessoaJuridica pessoa) {
        repository.criar(pessoa);
        return "redirect:/vendas/list";
    }
}

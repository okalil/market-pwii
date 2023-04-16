package com.aulajpa.controller;

import com.aulajpa.model.entity.Endereco;
import com.aulajpa.model.entity.PessoaFisica;
import com.aulajpa.model.entity.PessoaJuridica;
import com.aulajpa.model.repository.EnderecoRepository;
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
    PessoaRepository pessoaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

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
        pessoaRepository.criar(pessoa);
        for (Endereco endereco : pessoa.getEnderecos()) {
            endereco.setPessoa(pessoa);
            enderecoRepository.criar(endereco);
        };
        return "redirect:/vendas";
    }

    @PostMapping("/juridica/cadastrar")
    public String cadastrar(PessoaJuridica pessoa) {
        pessoaRepository.criar(pessoa);
        return "redirect:/vendas";
    }
}

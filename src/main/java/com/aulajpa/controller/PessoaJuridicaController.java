package com.aulajpa.controller;

import com.aulajpa.model.entity.Endereco;
import com.aulajpa.model.entity.PessoaJuridica;
import com.aulajpa.model.repository.EnderecoRepository;
import com.aulajpa.model.repository.PessoaJuridicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping("pj")
public class PessoaJuridicaController {

    @Autowired
    PessoaJuridicaRepository pessoaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping("/cadastrar")
    public String juridica() {
        return "/pessoas/pj-form";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(PessoaJuridica pessoa) {
        pessoaRepository.criar(pessoa);
        for (Endereco endereco : pessoa.getEnderecos()) {
            endereco.setPessoa(pessoa);
            enderecoRepository.criar(endereco);
        }
        return "redirect:/";
    }
}

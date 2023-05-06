package com.aulajpa.controller;

import com.aulajpa.model.entity.PessoaFisica;
import com.aulajpa.model.repository.EnderecoRepository;
import com.aulajpa.model.repository.PessoaFisicaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pf")
@Controller
public class PessoaFisicaController {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping("/cadastrar")
    public String fisica(PessoaFisica pessoaFisica) {
        return "/pessoas/pf-form";
    }

    @Transactional
    @PostMapping("/cadastrar")
    public String cadastrar(@Valid PessoaFisica pessoaFisica, BindingResult result) {
        if(result.hasErrors()) {
            return fisica(pessoaFisica);
        };
        pessoaFisicaRepository.criar(pessoaFisica);
        return "redirect:/";
    }
}

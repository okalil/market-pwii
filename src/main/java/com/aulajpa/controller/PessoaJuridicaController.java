package com.aulajpa.controller;

import com.aulajpa.model.entity.Endereco;
import com.aulajpa.model.entity.PessoaFisica;
import com.aulajpa.model.entity.PessoaJuridica;
import com.aulajpa.model.repository.EnderecoRepository;
import com.aulajpa.model.repository.PessoaJuridicaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public String juridica(PessoaJuridica pessoaJuridica) {
        return "/pessoas/pj-form";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid PessoaJuridica pessoaJuridica, BindingResult result) {
        if(result.hasErrors()) {
            return juridica(pessoaJuridica);
        };

        pessoaRepository.criar(pessoaJuridica);

        return "redirect:/";
    }
}

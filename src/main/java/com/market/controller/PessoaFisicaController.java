package com.market.controller;

import com.market.model.entity.PessoaFisica;
import com.market.model.repository.EnderecoRepository;
import com.market.model.repository.PessoaFisicaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/pf")
@Controller
public class PessoaFisicaController {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping
    public String listar(ModelMap model, @RequestParam(value = "nome", required = false) String nome) {
        if (nome == null || nome.isEmpty())
            model.addAttribute("pessoas", pessoaFisicaRepository.todos());
        else
            model.addAttribute("pessoas", pessoaFisicaRepository.todosPorNome(nome));

        return "pf/list";
    }

    @GetMapping("/cadastrar")
    public String fisica(PessoaFisica pessoaFisica) {
        return "/pf/form";
    }

    @Transactional
    @PostMapping("/cadastrar")
    public String cadastrar(@Valid PessoaFisica pessoaFisica, BindingResult result) {
        if (result.hasErrors()) {
            return fisica(pessoaFisica);
        }
        ;
        pessoaFisicaRepository.criar(pessoaFisica);
        return "redirect:/";
    }
}

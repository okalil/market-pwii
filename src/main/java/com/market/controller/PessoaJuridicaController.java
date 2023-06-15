package com.market.controller;

import com.market.model.entity.PessoaJuridica;
import com.market.model.entity.Role;
import com.market.model.repository.EnderecoRepository;
import com.market.model.repository.PessoaJuridicaRepository;
import com.market.model.repository.RoleRepository;
import com.market.model.repository.UsuarioRepository;
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

import java.util.Arrays;

@Transactional
@Controller
@RequestMapping("pj")
public class PessoaJuridicaController {
    @Autowired
    PessoaJuridicaRepository pessoaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public String listar(ModelMap model, @RequestParam(value = "nome", required = false) String nome) {
        if (nome == null || nome.isEmpty())
            model.addAttribute("pessoas", pessoaRepository.todos());
        else
            model.addAttribute("pessoas", pessoaRepository.todosPorNome(nome));

        return "pj/list";
    }

    @GetMapping("/cadastrar")
    public String juridica(PessoaJuridica pessoaJuridica) {
        return "/pj/form";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid PessoaJuridica pessoaJuridica, BindingResult result) {
        if(result.hasErrors()) {
            return juridica(pessoaJuridica);
        };

        if (usuarioRepository.usuario(pessoaJuridica.getUsuario().getUsuario()) != null) {
            result.rejectValue("usuario.usuario", "usuario.usuario", "Nome de usuário já usado!");
            return juridica(pessoaJuridica);
        }

        Role role = roleRepository.findByName("USER");
        pessoaJuridica.getUsuario().setRoles(Arrays.asList(role));

        pessoaRepository.criar(pessoaJuridica);

        return "redirect:/";
    }
}

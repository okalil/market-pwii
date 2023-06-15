package com.market.controller;

import com.market.model.entity.PessoaFisica;
import com.market.model.entity.Role;
import com.market.model.repository.EnderecoRepository;
import com.market.model.repository.PessoaFisicaRepository;
import com.market.model.repository.RoleRepository;
import com.market.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/pf")
@Controller
public class PessoaFisicaController {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public String listar(ModelMap model, @RequestParam(value = "nome", required = false) String nome) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("authenticated", true);
            model.addAttribute("usuario", authentication.getName());
        }
        List<Role> roles = (List<Role>) authentication.getAuthorities();
        model.addAttribute("isAdmin", roles.stream().filter(it -> "ADMIN".equals(it.getNome())).findFirst().orElse(null));

        if (nome == null || nome.isEmpty())
            model.addAttribute("pessoas", pessoaFisicaRepository.todos());
        else
            model.addAttribute("pessoas", pessoaFisicaRepository.todosPorNome(nome));

        return "pf/list";
    }

    @GetMapping("/cadastrar")
    public String fisica(PessoaFisica pessoaFisica, ModelMap model) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("authenticated", true);
            model.addAttribute("usuario", authentication.getName());
        }
        List<Role> roles = (List<Role>) authentication.getAuthorities();
        model.addAttribute("isAdmin", roles.stream().filter(it -> "ADMIN".equals(it.getNome())).findFirst().orElse(null));

        return "/pf/form";
    }

    @Transactional
    @PostMapping("/cadastrar")
    public String cadastrar(@Valid PessoaFisica pessoaFisica, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return fisica(pessoaFisica, model);
        }
        ;


        if (usuarioRepository.usuario(pessoaFisica.getUsuario().getUsuario()) != null) {
            result.rejectValue("usuario.usuario", "usuario.usuario", "Nome de usuário já usado!");
            return fisica(pessoaFisica, model);
        }

        Role role = roleRepository.findByName("USER");
        pessoaFisica.getUsuario().setRoles(Arrays.asList(role));

        pessoaFisicaRepository.criar(pessoaFisica);
        return "redirect:/";
    }
}

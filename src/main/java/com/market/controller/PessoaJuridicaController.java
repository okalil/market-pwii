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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

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
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("authenticated", true);
            model.addAttribute("usuario", authentication.getName());
        }
        List<Role> roles = (List<Role>) authentication.getAuthorities();
        model.addAttribute("isAdmin", roles.stream().filter(it -> "ADMIN".equals(it.getNome())).findFirst().orElse(null));

        if (nome == null || nome.isEmpty())
            model.addAttribute("pessoas", pessoaRepository.todos());
        else
            model.addAttribute("pessoas", pessoaRepository.todosPorNome(nome));

        return "pj/list";
    }

    @GetMapping("/cadastrar")
    public String juridica(PessoaJuridica pessoaJuridica, ModelMap model) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("authenticated", true);
            model.addAttribute("usuario", authentication.getName());
        }
        List<Role> roles = (List<Role>) authentication.getAuthorities();
        model.addAttribute("isAdmin", roles.stream().filter(it -> "ADMIN".equals(it.getNome())).findFirst().orElse(null));

        return "/pj/form";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid PessoaJuridica pessoaJuridica, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            return juridica(pessoaJuridica, model);
        };

        if (usuarioRepository.usuario(pessoaJuridica.getUsuario().getUsuario()) != null) {
            result.rejectValue("usuario.usuario", "usuario.usuario", "Nome de usuário já usado!");
            return juridica(pessoaJuridica, model);
        }

        Role role = roleRepository.findByName("USER");
        pessoaJuridica.getUsuario().setRoles(Arrays.asList(role));

        pessoaRepository.criar(pessoaJuridica);

        return "redirect:/";
    }
}

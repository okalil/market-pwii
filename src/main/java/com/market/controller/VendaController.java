package com.market.controller;

import com.market.model.entity.Role;
import com.market.model.repository.ProdutoRepository;
import com.market.model.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;


@Transactional
@Controller
@RequestMapping("vendas")
public class VendaController {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping
    public ModelAndView listar(ModelMap model, @RequestParam(value = "data", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate data) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("authenticated", true);
            model.addAttribute("usuario", authentication.getName());
            List<Role> roles = (List<Role>) authentication.getAuthorities();
            model.addAttribute("isAdmin", roles.stream().filter(it -> "ADMIN".equals(it.getNome())).findFirst().orElse(null));
        } else {
            model.addAttribute("isAdmin", false);
        }
        if (data == null)
            model.addAttribute("vendas", vendaRepository.todos());
        else
            model.addAttribute("vendas", vendaRepository.todosPorData(data));
        return new ModelAndView("/vendas/list", model);
    }

    @GetMapping("{vendaId}")
    public ModelAndView detalhes(ModelMap model, @PathVariable("vendaId") int vendaId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("authenticated", true);
            model.addAttribute("usuario", authentication.getName());
            List<Role> roles = (List<Role>) authentication.getAuthorities();
            model.addAttribute("isAdmin", roles.stream().filter(it -> "ADMIN".equals(it.getNome())).findFirst().orElse(null));
        } else {
            model.addAttribute("isAdmin", false);
        }
        model.addAttribute("venda", vendaRepository.buscarUm(vendaId));
        return new ModelAndView("/vendas/details", model);
    }

}

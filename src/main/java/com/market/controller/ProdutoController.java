package com.market.controller;

import com.market.model.entity.Produto;
import com.market.model.entity.Role;
import com.market.model.repository.ProdutoRepository;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    @GetMapping
    public ModelAndView index(ModelMap model, Produto produto) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("authenticated", true);
            model.addAttribute("usuario", authentication.getName());
            List<Role> roles = (List<Role>) authentication.getAuthorities();
            model.addAttribute("isAdmin", roles.stream().filter(it -> "ADMIN".equals(it.getNome())).findFirst().orElse(null));
        } else {
            model.addAttribute("isAdmin", false);
        }
        model.addAttribute("produtos", repository.todos());
        return new ModelAndView("/produtos/index", model);
    }

    @PostMapping
    public ModelAndView form(@Valid Produto produto, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            return index(model, produto);
        }
        repository.criar(produto);
        return new ModelAndView("redirect:/produtos");
    }
}

package com.aulajpa.controller;

import com.aulajpa.model.entity.Produto;
import com.aulajpa.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    @GetMapping
    public ModelAndView index(ModelMap model) {
        model.addAttribute("produtos", repository.todos());
        return new ModelAndView("/produtos/index", model);
    }

    @PostMapping
    public String form(Produto produto) {
        repository.criar(produto);
        return "redirect:/produtos";
    }
}

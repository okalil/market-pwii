package com.aulajpa.controller;

import com.aulajpa.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping
    public ModelAndView index(ModelMap model) {
        model.addAttribute("produtos", produtoRepository.todos());
        return new ModelAndView("/home", model);
    }
}

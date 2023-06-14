package com.market.controller;

import com.market.model.entity.Produto;
import com.market.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    public ModelAndView index(ModelMap model, Produto produto) {
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

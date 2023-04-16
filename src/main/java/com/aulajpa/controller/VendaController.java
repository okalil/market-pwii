package com.aulajpa.controller;

import com.aulajpa.model.repository.ProdutoRepository;
import com.aulajpa.model.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Transactional
@Controller
@RequestMapping("vendas")
public class VendaController {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("vendas", vendaRepository.todos());
        return new ModelAndView("/vendas/list", model);
    }

    @GetMapping("{vendaId}")
    public ModelAndView detalhes(ModelMap model, @PathVariable("vendaId") int vendaId) {
        model.addAttribute("venda", vendaRepository.buscarUm(vendaId));
        return new ModelAndView("/vendas/details", model);
    };
}

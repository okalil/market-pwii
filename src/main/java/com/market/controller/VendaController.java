package com.market.controller;

import com.market.model.repository.ProdutoRepository;
import com.market.model.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;


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
        if (data == null)
            model.addAttribute("vendas", vendaRepository.todos());
        else
            model.addAttribute("vendas", vendaRepository.todosPorData(data));
        return new ModelAndView("/vendas/list", model);
    }

    @GetMapping("{vendaId}")
    public ModelAndView detalhes(ModelMap model, @PathVariable("vendaId") int vendaId) {
        model.addAttribute("venda", vendaRepository.buscarUm(vendaId));
        return new ModelAndView("/vendas/details", model);
    }

}

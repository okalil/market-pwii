package com.aulajpa.controller;

import com.aulajpa.model.entity.ItemVenda;
import com.aulajpa.model.entity.Produto;
import com.aulajpa.model.entity.Venda;
import com.aulajpa.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Scope("request")
@Transactional
@Controller
@RequestMapping
public class HomeController {
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    Venda venda;

    @GetMapping
    public ModelAndView index(ModelMap model) {
        model.addAttribute("produtos", produtoRepository.todos());
        return new ModelAndView("/home", model);
    }
    @PostMapping
    public ModelAndView adicionar(@Valid ItemVenda itemVenda, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            return index(model);
        }

        List<ItemVenda> itens = venda.getItens();
        int produtoId = itemVenda.getProduto().getId();

        ItemVenda existingItem = itens.stream()
                .filter(item -> item.getProduto().getId() == produtoId)
                .findFirst()
                .orElse(null);
        if (existingItem != null) {
            existingItem.setQtd(existingItem.getQtd() + itemVenda.getQtd());
        } else {
            Produto produto = produtoRepository.buscarUm(produtoId);
            itemVenda.setProduto(produto);
            itemVenda.setVenda(venda);
            itens.add(itemVenda);
        }

        return new ModelAndView("redirect:/");
    }

}

package br.ufjf.dcc193.trbo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufjf.dcc193.trbo.models.Atendente;
import br.ufjf.dcc193.trbo.repositorys.AtendenteRepository;

/**
 * AtendenteController
 */
@Controller
public class AtendenteController {

    @Autowired
    AtendenteRepository atendenteRepo;

    // CHAMA TELA CRIAR ATENDENTE
    @RequestMapping("/atendente/criar.html")
    public String criar() {
        return "/atendente/criar.html";
    }
    
    // CRIA ATENDENTE
    @RequestMapping(value = "/atendente/criar.html", method = RequestMethod.POST)
    public String criar(Atendente atendente) {
        atendenteRepo.save(atendente);
        return "redirect:/atendente/listar.html";
    }

    // LISTA ATENDENTES
    @RequestMapping("/atendente/listar.html")
    public String listar(Model model) {
        List<Atendente> listaAtendentes = atendenteRepo.findAll();
        if (listaAtendentes != null) {
            model.addAttribute("atendentes", listaAtendentes);
        }
        return "atendente/listar.html";
    }
}
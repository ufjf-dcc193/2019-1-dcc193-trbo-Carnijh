package br.ufjf.dcc193.trbo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "redirect:/index.html";
    }

    // LISTA ATENDENTES
    @RequestMapping("/atendente/listar.html")
    public String listar(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            List<Atendente> listaAtendentes = atendenteRepo.findAll();
            if (listaAtendentes != null) {
                model.addAttribute("atendentes", listaAtendentes);
            }
            return "atendente/listar.html";
        }
        return "redirect:/index.html";
    }

    // CHAMA A TELA EDITAR ATENDENTE
    @RequestMapping(value = "/atendente/editar.html/{id}")
    public String editar(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendente atendente = atendenteRepo.findById(id).get();
            model.addAttribute("atendente", atendente);
            return "/atendente/editar.html";
        }
        return "redirect:/index.html";
    }

    // EDITA ATENDENTE
    @RequestMapping(value = "/atendente/editar.html/{id}", method = RequestMethod.POST)
    public String editar(Atendente atendente, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            atendenteRepo.save(atendente);
            return "redirect:/atendente/listar.html";
        }
        return "redirect:/index.html";
    }

    // DELETA ATENDENTE
    @RequestMapping(value = "/atendente/deletar.html/{id}")
    public String deletar(@PathVariable("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            atendenteRepo.deleteById(id);
            return "redirect:/atendente/listar.html";
        }
        return "redirect:/index.html";
    }
}
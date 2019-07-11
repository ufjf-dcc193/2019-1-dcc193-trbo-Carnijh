package br.ufjf.dcc193.trbo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufjf.dcc193.trbo.models.Atendente;
import br.ufjf.dcc193.trbo.repositorys.AtendenteRepository;

/**
 * HomeController
 */
@Controller
public class HomeController {

    @Autowired
    AtendenteRepository atendenteRepo;

    // CHAMA TELA INDEX
    @RequestMapping({"/", "/index.html"})
    public String login() {
        return "index.html";
    }

    // FAZ LOGIN
    @RequestMapping(value = {"/", "/index.html"}, method = RequestMethod.POST)
    public String login(Atendente atendente, HttpServletRequest request) {
        Atendente atendenteAux = atendenteRepo.findByEmailAndCodigoAcesso(atendente.getEmail(), atendente.getCodigoAcesso());
        if(atendenteAux != null) {
            request.getSession().setAttribute("atendenteLogado", atendente);
            return "redirect:/menu.html";
        }
        return "index.html";
    }

    // CHAMA TELA MENU
    @RequestMapping("/menu.html")
    public String menu(HttpServletRequest request) {
        if(request.getSession().getAttribute("atendenteLogado") != null){
            return "menu.html";
        }
        return "redirect:/index.html";
    }

}
package br.ufjf.dcc193.trbo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AtendenteController
 */
@Controller
public class AtendenteController {
    // CHAMA TELA CRIAR ATENDENTE
    @RequestMapping("/atendente/criar.html")
    public String criar() {
        return "/atendente/criar.html";
    }
}
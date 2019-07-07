package br.ufjf.dcc193.trbo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 */
@Controller
public class HomeController {

    // CHAMA TELA MENU
    @RequestMapping("/menu.html")
    public String menu() {
        return "/menu.html";
    }

}
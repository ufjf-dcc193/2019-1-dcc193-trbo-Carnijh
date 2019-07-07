package br.ufjf.dcc193.trbo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufjf.dcc193.trbo.models.Categoria;
import br.ufjf.dcc193.trbo.repositorys.CategoriaRepository;

/**
 * CategoriaController
 */
@Controller
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepo;

    // CHAMA TELA CRIAR CATEGORIA
    @RequestMapping("/categoria/criar.html")
    public String criar() {
        return "/categoria/criar.html";
    }
    
    // CRIA CATEGORIA
    @RequestMapping(value = "/categoria/criar.html", method = RequestMethod.POST)
    public String criar(Categoria categoria) {
        categoriaRepo.save(categoria);
        return "redirect:/categoria/listar.html";
    }

    // LISTA CATEGORIAS
    @RequestMapping("/categoria/listar.html")
    public String listar(Model model) {
        List<Categoria> listaCategorias = categoriaRepo.findAll();
        if (listaCategorias != null) {
            model.addAttribute("categorias", listaCategorias);
        }
        return "categoria/listar.html";
    }

    //CHAMA A TELA EDITAR CATEGORIA
    @RequestMapping(value = "/categoria/editar.html/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Categoria categoria = categoriaRepo.findById(id).get();
        model.addAttribute("categoria", categoria);
        return "/categoria/editar.html";
    }

    //EDITA CATEGORIA
    @RequestMapping(value = "/categoria/editar.html/{id}", method = RequestMethod.POST)
    public String editar(Categoria categoria) {
        categoriaRepo.save(categoria);
        return "redirect:/categoria/listar.html";
    }

    //DELETA CATEGORIA
    @RequestMapping(value = "/categoria/deletar.html/{id}")
    public String deletar(@PathVariable("id") Long id) {
        categoriaRepo.deleteById(id);
        return "redirect:/categoria/listar.html";
    }
}
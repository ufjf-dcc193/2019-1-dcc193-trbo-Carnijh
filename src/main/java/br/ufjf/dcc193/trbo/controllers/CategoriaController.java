package br.ufjf.dcc193.trbo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufjf.dcc193.trbo.models.Atendimento;
import br.ufjf.dcc193.trbo.models.Categoria;
import br.ufjf.dcc193.trbo.repositorys.AtendimentoRepository;
import br.ufjf.dcc193.trbo.repositorys.CategoriaRepository;

/**
 * CategoriaController
 */
@Controller
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepo;

    @Autowired
    AtendimentoRepository atendimentoRepo;

    // CHAMA TELA CRIAR CATEGORIA
    @RequestMapping("/categoria/criar.html")
    public String criar(HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            return "categoria/criar.html";
        }
        return "redirect:/index.html";
    }

    // CRIA CATEGORIA
    @RequestMapping(value = "/categoria/criar.html", method = RequestMethod.POST)
    public String criar(Categoria categoria, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            categoria.setDeletado(false);
            categoriaRepo.save(categoria);
            return "redirect:/categoria/listar.html";
        }
        return "redirect:/index.html";
    }

    // LISTA CATEGORIAS
    @RequestMapping("/categoria/listar.html")
    public String listar(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            List<Categoria> listaCategorias = categoriaRepo.findByDeletado(false);
            if (listaCategorias != null) {
                model.addAttribute("categorias", listaCategorias);
            }
            return "categoria/listar.html";
        }
        return "redirect:/index.html";
    }

    // CHAMA A TELA EDITAR CATEGORIA
    @RequestMapping(value = "/categoria/editar.html/{id}")
    public String editar(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Categoria categoria = categoriaRepo.findById(id).get();
            model.addAttribute("categoria", categoria);
            return "categoria/editar.html";
        }
        return "redirect:/index.html";
    }

    // EDITA CATEGORIA
    @RequestMapping(value = "/categoria/editar.html/{id}", method = RequestMethod.POST)
    public String editar(Categoria categoria, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            categoriaRepo.save(categoria);
            return "redirect:/categoria/listar.html";
        }
        return "redirect:/index.html";
    }

    // DELETA CATEGORIA
    @RequestMapping(value = "/categoria/deletar.html/{id}")
    public String deletar(@PathVariable("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Categoria categoria = categoriaRepo.findById(id).get();
            List<Atendimento> atendimentos = atendimentoRepo.findByCategoria(categoria);
            if (atendimentos == null) {
                categoriaRepo.delete(categoria);
            }
            else{
                categoria.setDeletado(true);
                categoriaRepo.save(categoria);
            }
            return "redirect:/categoria/listar.html";
        }
        return "redirect:/index.html";
    }
}
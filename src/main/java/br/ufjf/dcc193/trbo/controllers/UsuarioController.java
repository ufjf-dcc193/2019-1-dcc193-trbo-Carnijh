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
import br.ufjf.dcc193.trbo.models.Usuario;
import br.ufjf.dcc193.trbo.repositorys.AtendimentoRepository;
import br.ufjf.dcc193.trbo.repositorys.UsuarioRepository;

/**
 * UsuarioController
 */
@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    AtendimentoRepository atendimentoRepo;

    // CHAMA TELA CRIAR USUARIO
    @RequestMapping("/usuario/criar.html")
    public String criar(HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            return "usuario/criar.html";
        }
        return "redirect:/index.html";
    }

    // CRIA USUARIO
    @RequestMapping(value = "/usuario/criar.html", method = RequestMethod.POST)
    public String criar(Usuario usuario, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            usuarioRepo.save(usuario);
            return "redirect:/usuario/listar.html";
        }
        return "redirect:/index.html";
    }

    // LISTA USUARIOS
    @RequestMapping("/usuario/listar.html")
    public String listar(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            List<Usuario> listaUsuarios = usuarioRepo.findByDeletado(false);
            if (listaUsuarios != null) {
                model.addAttribute("usuarios", listaUsuarios);
            }
            return "usuario/listar.html";
        }
        return "redirect:/index.html";
    }

    // CHAMA A TELA EDITAR USUARIO
    @RequestMapping(value = "/usuario/editar.html/{id}")
    public String editar(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Usuario usuario = usuarioRepo.findById(id).get();
            model.addAttribute("usuario", usuario);
            return "usuario/editar.html";
        }
        return "redirect:/index.html";
    }

    // EDITA USUARIO
    @RequestMapping(value = "/usuario/editar.html/{id}", method = RequestMethod.POST)
    public String editar(Usuario usuario, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            usuarioRepo.save(usuario);
            return "redirect:/usuario/listar.html";
        }
        return "redirect:/index.html";
    }

    // DELETA USUARIO
    @RequestMapping(value = "/usuario/deletar.html/{id}")
    public String deletar(@PathVariable("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Usuario usuario = usuarioRepo.findById(id).get();
            List<Atendimento> atendimentos = atendimentoRepo.findByUsuario(usuario);
            if (atendimentos == null) {
                usuarioRepo.delete(usuario);
            }
            else{
                usuario.setDeletado(true);
                usuarioRepo.save(usuario);
            }
            return "redirect:/usuario/listar.html";
        }
        return "redirect:/index.html";
    }
}
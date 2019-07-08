package br.ufjf.dcc193.trbo.controllers;

import java.util.List;

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
    public String criar() {
        return "/usuario/criar.html";
    }
    
    // CRIA USUARIO
    @RequestMapping(value = "/usuario/criar.html", method = RequestMethod.POST)
    public String criar(Usuario usuario) {
        usuarioRepo.save(usuario);
        return "redirect:/usuario/listar.html";
    }

    // LISTA USUARIOS
    @RequestMapping("/usuario/listar.html")
    public String listar(Model model) {
        List<Usuario> listaUsuarios = usuarioRepo.findAll();
        if (listaUsuarios != null) {
            model.addAttribute("usuarios", listaUsuarios);
        }
        return "usuario/listar.html";
    }

    //CHAMA A TELA EDITAR USUARIO
    @RequestMapping(value = "/usuario/editar.html/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioRepo.findById(id).get();
        model.addAttribute("usuario", usuario);
        return "/usuario/editar.html";
    }

    //EDITA USUARIO
    @RequestMapping(value = "/usuario/editar.html/{id}", method = RequestMethod.POST)
    public String editar(Usuario usuario) {
        usuarioRepo.save(usuario);
        return "redirect:/usuario/listar.html";
    }

    //DELETA USUARIO
    @RequestMapping(value = "/usuario/deletar.html/{id}")
    public String deletar(@PathVariable("id") Long id) {
        usuarioRepo.deleteById(id);
        return "redirect:/usuario/listar.html";
    }

    //CHAMA A TELA DETALHAR USUARIO
    @RequestMapping(value = "/usuario/detalhar.html/{id}")
    public String detalhar(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioRepo.findById(id).get();
        List<Atendimento> atendimentos = atendimentoRepo.findByUsuario(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("atendimentos", atendimentos);
        return "/usuario/detalhar.html";
    }
}
package br.ufjf.dcc193.trbo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufjf.dcc193.trbo.models.Atendente;
import br.ufjf.dcc193.trbo.models.Atendimento;
import br.ufjf.dcc193.trbo.models.Categoria;
import br.ufjf.dcc193.trbo.models.Evento;
import br.ufjf.dcc193.trbo.models.Usuario;
import br.ufjf.dcc193.trbo.repositorys.AtendimentoRepository;
import br.ufjf.dcc193.trbo.repositorys.CategoriaRepository;
import br.ufjf.dcc193.trbo.repositorys.EventoRepository;
import br.ufjf.dcc193.trbo.repositorys.UsuarioRepository;

/**
 * AtendimentoController
 */
@Controller
public class AtendimentoController {

    @Autowired
    AtendimentoRepository atendimentoRepo;
    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    CategoriaRepository categoriaRepo;
    @Autowired
    EventoRepository eventoRepo;

    // CHAMA TELA CRIAR ATENDIMENTO
    @RequestMapping("/atendimento/criar.html")
    public String criar(Model model) {
        List<Usuario> listaUsuarios = usuarioRepo.findAll();
        if (listaUsuarios != null) {
            model.addAttribute("usuarios", listaUsuarios);
        }
        List<Categoria> listaCategorias = categoriaRepo.findAll();
        if (listaCategorias != null) {
            model.addAttribute("categorias", listaCategorias);
        }
        return "/atendimento/criar.html";
    }
    
    // CRIA ATENDIMENTO
    @RequestMapping(value = "/atendimento/criar.html", method = RequestMethod.POST)
    public String criar(Atendimento atendimento, HttpSession session) {
        // Pega data atual
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");  
        Date data = new Date();
        atendimento.setDataCriacao(formataData.format(data));
        // Pega hora atual
        SimpleDateFormat formataHora = new SimpleDateFormat("HH:mm:ss");  
        atendimento.setHoraCriacao(formataHora.format(data));
        // Seta Atendente
        atendimento.setAtendente((Atendente)session.getAttribute("atendenteLogado"));
        // Seta status
        atendimento.setStatus("Em revisão");
        atendimentoRepo.save(atendimento);
        return "redirect:/evento/criar.html/"+atendimento.getId();
    }

    // LISTA ATENDIMENTOS
    @RequestMapping("/atendimento/listar.html")
    public String listar(Model model) {
        List<Atendimento> listaAtendimentos = atendimentoRepo.findAll();
        if (listaAtendimentos != null) {
            model.addAttribute("atendimentos", listaAtendimentos);
        }
        return "atendimento/listar.html";
    }

    //CHAMA A TELA DETALHAR ATENDIMENTO
    @RequestMapping(value = "/atendimento/detalhar.html/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Atendimento atendimento = atendimentoRepo.findById(id).get();
        model.addAttribute("atendimento", atendimento);
        return "/atendimento/detalhar.html";
    }
}
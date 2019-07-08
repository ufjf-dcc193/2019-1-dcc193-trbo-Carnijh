package br.ufjf.dcc193.trbo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import br.ufjf.dcc193.trbo.models.Usuario;
import br.ufjf.dcc193.trbo.repositorys.AtendenteRepository;
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
    @Autowired
    AtendenteRepository atendenteRepo;

    // CHAMA TELA CRIAR ATENDIMENTO
    @RequestMapping("/atendimento/criar.html")
    public String criar(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
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
        return "redirect:/index.html";
    }

    // CRIA ATENDIMENTO
    @RequestMapping(value = "/atendimento/criar.html", method = RequestMethod.POST)
    public String criar(Atendimento atendimento, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            // Pega data atual
            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
            Date data = new Date();
            atendimento.setDataCriacao(formataData.format(data));
            // Pega hora atual
            SimpleDateFormat formataHora = new SimpleDateFormat("HH:mm:ss");
            atendimento.setHoraCriacao(formataHora.format(data));
            // Seta Atendente
            atendimento.setAtendente((Atendente) request.getSession().getAttribute("atendenteLogado"));
            // Seta status
            atendimento.setStatus("Em revisão");
            atendimentoRepo.save(atendimento);
            return "redirect:/evento/criar.html/" + atendimento.getId();
        }
        return "redirect:/index.html";
    }

    // LISTA ATENDIMENTOS
    @RequestMapping("/atendimento/listar.html")
    public String listar(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            List<Atendimento> listaAtendimentos = atendimentoRepo.findAll();
            List<Categoria> listaCategorias = categoriaRepo.findAll();
            List<Atendente> listaAtendentes = atendenteRepo.findAll();
            if (listaAtendimentos != null) {
                model.addAttribute("atendimentos", listaAtendimentos);
            }
            if (listaAtendentes != null) {
                model.addAttribute("atendentes", listaAtendentes);
            }
            if (listaCategorias != null) {
                model.addAttribute("categorias", listaCategorias);
            }
            return "atendimento/listar.html";
        }
        return "redirect:/index.html";
    }

    // LISTA ATENDIMENTOS POR ATENDENTE
    @RequestMapping("/atendimento/listar.html/atendente/{id}")
    public String listarByAtendente(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            List<Categoria> listaCategorias = categoriaRepo.findAll();
            List<Atendente> listaAtendentes = atendenteRepo.findAll();
            if (listaAtendentes != null) {
                model.addAttribute("atendentes", listaAtendentes);
            }
            if (listaCategorias != null) {
                model.addAttribute("categorias", listaCategorias);
            }
            Optional<Atendente> atendenteAux = atendenteRepo.findById(id);
            Atendente atendente = atendenteAux.orElse(new Atendente());
            List<Atendimento> atendimentos = atendimentoRepo.findByAtendenteAndStatus(atendente, "Em revisão");
            List<Atendimento> atendimentos2 = atendimentoRepo.findByAtendenteAndStatus(atendente, "Aberto");
            List<Atendimento> atendimentos3 = atendimentoRepo.findByAtendenteAndStatus(atendente, "Bloqueado");
            List<Atendimento> atendimentos4 = atendimentoRepo.findByAtendenteAndStatus(atendente, "Em andamento");
            atendimentos.addAll(atendimentos2);
            atendimentos.addAll(atendimentos3);
            atendimentos.addAll(atendimentos4);
            if (atendimentos != null) {
                model.addAttribute("atendimentos", atendimentos);
            }
            return "atendimento/listar.html";
        }
        return "redirect:/index.html";
    }

    // LISTA ATENDIMENTOS POR CATEGORIA
    @RequestMapping("/atendimento/listar.html/categoria/{id}")
    public String listarByCategoria(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            List<Categoria> listaCategorias = categoriaRepo.findAll();
            List<Atendente> listaAtendentes = atendenteRepo.findAll();
            if (listaAtendentes != null) {
                model.addAttribute("atendentes", listaAtendentes);
            }
            if (listaCategorias != null) {
                model.addAttribute("categorias", listaCategorias);
            }
            Optional<Categoria> categoriaAux = categoriaRepo.findById(id);
            Categoria categoria = categoriaAux.orElse(new Categoria());
            List<Atendimento> atendimentos = atendimentoRepo.findByCategoriaAndStatus(categoria, "Em revisão");
            List<Atendimento> atendimentos2 = atendimentoRepo.findByCategoriaAndStatus(categoria, "Aberto");
            List<Atendimento> atendimentos3 = atendimentoRepo.findByCategoriaAndStatus(categoria, "Bloqueado");
            List<Atendimento> atendimentos4 = atendimentoRepo.findByCategoriaAndStatus(categoria, "Em andamento");
            atendimentos.addAll(atendimentos2);
            atendimentos.addAll(atendimentos3);
            atendimentos.addAll(atendimentos4);
            if (atendimentos != null) {
                model.addAttribute("atendimentos", atendimentos);
            }
            return "atendimento/listar.html";
        }
        return "redirect:/index.html";
    }

    // LISTA ATENDIMENTOS FECHADOS
    @RequestMapping("/atendimento/listar.html/fechados")
    public String listarFechados(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            List<Categoria> listaCategorias = categoriaRepo.findAll();
            List<Atendente> listaAtendentes = atendenteRepo.findAll();
            if (listaAtendentes != null) {
                model.addAttribute("atendentes", listaAtendentes);
            }
            if (listaCategorias != null) {
                model.addAttribute("categorias", listaCategorias);
            }
            List<Atendimento> listaAtendimentos = atendimentoRepo.findByStatus("Fechado");
            if (listaAtendimentos != null) {
                model.addAttribute("atendimentos", listaAtendimentos);
            }
            return "atendimento/listar.html";
        }
        return "redirect:/index.html";
    }

    // CHAMA A TELA DETALHAR ATENDIMENTO
    @RequestMapping(value = "/atendimento/detalhar.html/{id}")
    public String editar(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendimento atendimento = atendimentoRepo.findById(id).get();
            model.addAttribute("atendimento", atendimento);
            return "/atendimento/detalhar.html";
        }
        return "redirect:/index.html";
    }
}
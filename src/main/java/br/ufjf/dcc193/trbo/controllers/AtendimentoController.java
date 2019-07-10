package br.ufjf.dcc193.trbo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufjf.dcc193.trbo.models.Atendente;
import br.ufjf.dcc193.trbo.models.Atendimento;
import br.ufjf.dcc193.trbo.models.Categoria;
import br.ufjf.dcc193.trbo.models.Evento;
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
            Atendente atendenteAux = (Atendente) request.getSession().getAttribute("atendenteLogado");
            Atendente atendente = atendenteRepo.findByEmailAndCodigoAcesso(atendenteAux.getEmail(),
                    atendenteAux.getCodigoAcesso());
            atendimento.setAtendente(atendente);
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
    public String detalhar(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendimento atendimento = atendimentoRepo.findById(id).get();
            model.addAttribute("atendimento", atendimento);
            return "/atendimento/detalhar.html";
        }
        return "redirect:/index.html";
    }

    // CHAMA A TELA EDITAR ATENDIMENTO USUARIO
    @RequestMapping(value = "/atendimento/editar.html/usuario/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendimento atendimento = atendimentoRepo.findById(id).get();
            List<Usuario> usuarios = usuarioRepo.findAll();
            model.addAttribute("atendimento", atendimento);
            model.addAttribute("usuarios", usuarios);
            return "/atendimento/editar.html";
        }
        return "redirect:/index.html";
    }

    // EDITA ATENDIMENTO USUARIO
    @RequestMapping(value = "/atendimento/editar.html/usuario/{id}", method = RequestMethod.POST)
    public String editarUsuario(
        @PathVariable("id") Long id,
        @RequestParam("usuario") Long usuarioId, 
        @RequestParam("motivo") String motivo, 
        HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendimento atendimento = atendimentoRepo.findById(id).get();
            Date data = new Date();
            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formataHora = new SimpleDateFormat("HH:mm:ss");
            String log;

            if (usuarioId == 0) {
                log = " - Usuário "+atendimento.getUsuario().getNomeCompleto()+" retirado.";
                atendimento.setUsuario(null);
            }
            else{
                Usuario usuario = usuarioRepo.findById(usuarioId).get();
                log = " - Usuário alterado de "+atendimento.getUsuario().getNomeCompleto()+" para "+usuario.getNomeCompleto()+".";
                atendimento.setUsuario(usuario);
            }
            
            atendimentoRepo.save(atendimento);

            Evento evento = new Evento();
            evento.setAtendimento(atendimento);
            evento.setData(formataData.format(data));
            evento.setHora(formataHora.format(data));
            evento.setTipo("Alteração de Usuário");
            evento.setDescricao(motivo+log);
            eventoRepo.save(evento);

            atendimento.addEvento(evento);
            atendimentoRepo.save(atendimento);
            return "redirect:/atendimento/detalhar.html/" + atendimento.getId();
        }
        return "redirect:/index.html";
    }

    // CHAMA A TELA EDITAR ATENDIMENTO ATENDENTE
    @RequestMapping(value = "/atendimento/editar.html/atendente/{id}")
    public String editarAtendente(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendimento atendimento = atendimentoRepo.findById(id).get();
            List<Atendente> atendentes = atendenteRepo.findAll();
            model.addAttribute("atendimento", atendimento);
            model.addAttribute("atendentes", atendentes);
            return "/atendimento/editar.html";
        }
        return "redirect:/index.html";
    }

    // EDITA ATENDIMENTO ATENDENTE
    @RequestMapping(value = "/atendimento/editar.html/atendente/{id}", method = RequestMethod.POST)
    public String editarAtendente(
        @PathVariable("id") Long id,
        @RequestParam("atendente") Long atendenteId, 
        @RequestParam("motivo") String motivo, 
        HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendimento atendimento = atendimentoRepo.findById(id).get();
            Atendente atendente = atendenteRepo.findById(atendenteId).get();
            String log = " - Atendente alterada de "+atendimento.getAtendente().getNomeCompleto()+" para "+atendente.getNomeCompleto()+".";
            atendimento.setAtendente(atendente);
            atendimentoRepo.save(atendimento);
            
            Date data = new Date();
            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formataHora = new SimpleDateFormat("HH:mm:ss");

            Evento evento = new Evento();
            evento.setAtendimento(atendimento);
            evento.setData(formataData.format(data));
            evento.setHora(formataHora.format(data));
            evento.setTipo("Alteração de Atendente");
            evento.setDescricao(motivo+log);
            eventoRepo.save(evento);

            atendimento.addEvento(evento);
            atendimentoRepo.save(atendimento);
            return "redirect:/atendimento/detalhar.html/" + atendimento.getId();
        }
        return "redirect:/index.html";
    }

    // CHAMA A TELA EDITAR ATENDIMENTO CATEGORIA
    @RequestMapping(value = "/atendimento/editar.html/categoria/{id}")
    public String editarCategoria(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendimento atendimento = atendimentoRepo.findById(id).get();
            List<Categoria> categorias = categoriaRepo.findAll();
            model.addAttribute("atendimento", atendimento);
            model.addAttribute("categorias", categorias);
            return "/atendimento/editar.html";
        }
        return "redirect:/index.html";
    }

    // EDITA ATENDIMENTO CATEGORIA
    @RequestMapping(value = "/atendimento/editar.html/categoria/{id}", method = RequestMethod.POST)
    public String editarCategoria(
        @PathVariable("id") Long id,
        @RequestParam("categoria") Long categoriaId, 
        @RequestParam("motivo") String motivo, 
        HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendimento atendimento = atendimentoRepo.findById(id).get();
            Categoria categoria = categoriaRepo.findById(categoriaId).get();
            String log = " - Categoria alterada de "+atendimento.getCategoria().getTitulo()+" para "+categoria.getTitulo()+".";
            atendimento.setCategoria(categoria);
            atendimentoRepo.save(atendimento);
            
            Date data = new Date();
            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formataHora = new SimpleDateFormat("HH:mm:ss");

            Evento evento = new Evento();
            evento.setAtendimento(atendimento);
            evento.setData(formataData.format(data));
            evento.setHora(formataHora.format(data));
            evento.setTipo("Alteração de Categoria");
            evento.setDescricao(motivo+log);
            eventoRepo.save(evento);

            atendimento.addEvento(evento);
            atendimentoRepo.save(atendimento);
            return "redirect:/atendimento/detalhar.html/" + atendimento.getId();
        }
        return "redirect:/index.html";
    }

    // CHAMA A TELA EDITAR ATENDIMENTO STATUS
    @RequestMapping(value = "/atendimento/editar.html/status/{id}")
    public String editarStatus(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendimento atendimento = atendimentoRepo.findById(id).get();
            model.addAttribute("atendimento", atendimento);
            model.addAttribute("status", true);
            return "/atendimento/editar.html";
        }
        return "redirect:/index.html";
    }

    // EDITA ATENDIMENTO STATUS
    @RequestMapping(value = "/atendimento/editar.html/status/{id}", method = RequestMethod.POST)
    public String editarStatus(
        @PathVariable("id") Long id,
        @RequestParam("status") String status, 
        @RequestParam("motivo") String motivo, 
        HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Atendimento atendimento = atendimentoRepo.findById(id).get();
            String log = " - Status alterada de "+atendimento.getStatus()+" para "+status+".";
            
            Date data = new Date();
            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formataHora = new SimpleDateFormat("HH:mm:ss");
            String dataFechamento = formataData.format(data);
            String horaFechamento = formataHora.format(data);

            if (status.contains("F")) {
                atendimento.setDataFechamento(dataFechamento);
                atendimento.setHoraFechamento(horaFechamento);
            }
            atendimento.setStatus(status);
            atendimentoRepo.save(atendimento);

            Evento evento = new Evento();
            evento.setAtendimento(atendimento);
            evento.setData(dataFechamento);
            evento.setHora(horaFechamento);
            evento.setTipo("Fechamento");
            evento.setDescricao(motivo+log);
            eventoRepo.save(evento);

            atendimento.addEvento(evento);
            atendimentoRepo.save(atendimento);
            return "redirect:/atendimento/detalhar.html/" + atendimento.getId();
        }
        return "redirect:/index.html";
    }
}
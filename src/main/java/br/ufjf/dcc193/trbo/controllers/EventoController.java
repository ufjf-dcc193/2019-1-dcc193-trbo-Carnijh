package br.ufjf.dcc193.trbo.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufjf.dcc193.trbo.models.Atendimento;
import br.ufjf.dcc193.trbo.models.Evento;
import br.ufjf.dcc193.trbo.repositorys.AtendimentoRepository;
import br.ufjf.dcc193.trbo.repositorys.EventoRepository;

/**
 * EventoController
 */
@Controller
public class EventoController {

    @Autowired
    EventoRepository eventoRepo;
    @Autowired
    AtendimentoRepository atendimentoRepo;

    // CRIA EVENTO
    @RequestMapping(value = "/evento/criar.html/{id}")
    public String criar(@PathVariable("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            Evento evento = new Evento();
            Optional<Atendimento> atendimentoAux = atendimentoRepo.findById(id);
            Atendimento atendimento = atendimentoAux.orElse(new Atendimento());
            evento.setAtendimento(atendimento);
            evento.setData(atendimento.getDataCriacao());
            evento.setHora(atendimento.getHoraCriacao());
            evento.setTipo("Abertura");
            evento.setDescricao(atendimento.getDescricao());
            eventoRepo.save(evento);
            atendimento.addEvento(evento);
            atendimentoRepo.save(atendimento);
            return "redirect:/atendimento/listar.html";
        }
        return "redirect:/index.html";
    }

    // LISTA EVENTOS
    @RequestMapping("/evento/listar.html")
    public String listar(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("atendenteLogado") != null) {
            List<Evento> listaEventos = eventoRepo.findAll();
            if (listaEventos != null) {
                model.addAttribute("eventos", listaEventos);
            }
            return "evento/listar.html";
        }
        return "redirect:/index.html";
    }

}
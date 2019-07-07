package br.ufjf.dcc193.trbo.models;

import java.util.Date;
import java.util.List;

/**
 * Atendimento
 */
public class Atendimento {
    private Long id;
    private Categoria categoria;
    private Date criacao;
    private Date fechamento; //opcional
    private String descricao;
    private Atendente atendente;
    private Usuario usuario; //opcional
    private String status; //Classe status ? (entre: em revisão; aberto; bloqueado; em andamento e fechado)
    private List<Evento> eventos;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getCriacao() {
		return this.criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public Date getFechamento() {
		return this.fechamento;
	}

	public void setFechamento(Date fechamento) {
		this.fechamento = fechamento;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Atendente getAtendente() {
		return this.atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

}
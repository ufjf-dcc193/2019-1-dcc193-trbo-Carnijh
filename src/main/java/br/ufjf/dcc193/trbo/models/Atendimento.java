package br.ufjf.dcc193.trbo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Atendimento
 */
@Entity
public class Atendimento {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@Column(nullable = false)
    private Categoria categoria;
	@Column(nullable = false)
    private String dataCriacao;
	@Column(nullable = false)
    private String horaCriacao;
    private String dataFechamento; //opcional
    private String horaFechamento; //opcional
	@Column(nullable = false)
	private String descricao;
	@ManyToOne
	@Column(nullable = false)
	private Atendente atendente;
	@ManyToOne
    private Usuario usuario; //opcional
	@Column(nullable = false)
    private String status; //Classe status ? (entre: em revisão; aberto; bloqueado; em andamento e fechado)
	@OneToMany
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

	public String getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getHoraCriacao() {
		return this.horaCriacao;
	}

	public void setHoraCriacao(String horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

	public String getDataFechamento() {
		return this.dataFechamento;
	}

	public void setDataFechamento(String dataFechamento) {
		this.dataFechamento = dataFechamento;
	}


	public String getHoraFechamento() {
		return this.horaFechamento;
	}

	public void setHoraFechamento(String horaFechamento) {
		this.horaFechamento = horaFechamento;
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

	public void addEvento(Evento evento){
		getEventos().add(evento);
	} 

}
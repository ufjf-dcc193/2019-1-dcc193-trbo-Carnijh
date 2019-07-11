package br.ufjf.dcc193.trbo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Evento
 */
@Entity
public class Evento {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
    private Atendimento atendimento;
	@Column(nullable = false)
    private String data;
	@Column(nullable = false)
    private String hora;
	@Column(nullable = false)
    private String tipo; // entre: abertura, fechamento, alteração de Usuário, alteração de Categoria, alteração de Atendente, alteração de status e uma descrição textual
	@Column(nullable = false)
	private String descricao;

	public Evento(){}

	public Evento(Atendimento atendimento, String data, String hora, String tipo, String descricao) {
		this.atendimento = atendimento;
		this.data = data;
		this.hora = hora;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Atendimento getAtendimento() {
		return this.atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
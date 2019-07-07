package br.ufjf.dcc193.trbo.models;

import java.util.Date;

/**
 * Evento
 */
public class Evento {
    private Long id;
    private Atendimento atendimento;
    private Date dataHora;
    private String tipo; // Class tipo ? (entre: abertura, fechamento, alteração de Usuário, alteração de Categoria, alteração de Atendente, alteração de status e uma descrição textual
    private String descricao; // Perguntar pro professor se faz parte do tipo.

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

	public Date getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
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
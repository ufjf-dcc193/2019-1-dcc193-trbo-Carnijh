package br.ufjf.dcc193.trbo.models;

/**
 * Atendente
 */
public class Atendente {
    private Long id;
    private String nomeCompleto;
    private String codigoAcesso;
    private String telefone;
    private String celular;
    private String email;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCodigoAcesso() {
		return this.codigoAcesso;
	}

	public void setCodigoAcesso(String codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
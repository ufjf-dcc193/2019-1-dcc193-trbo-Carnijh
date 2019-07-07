package br.ufjf.dcc193.trbo.models;

/**
 * Usuario
 */
public class Usuario {
    // nome completo, setor, código do vínculo, telefone, celular, e-mail.
    private Long id;
    private String nomeCompleto;
    private String setor;
    private String codigoVinculo;
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

	public String getSetor() {
		return this.setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getCodigoVinculo() {
		return this.codigoVinculo;
	}

	public void setCodigoVinculo(String codigoVinculo) {
		this.codigoVinculo = codigoVinculo;
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
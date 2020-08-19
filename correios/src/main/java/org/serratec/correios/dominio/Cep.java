package org.serratec.correios.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cep implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String numero;
	
	private String endereco;
	private String uf;
	private String cidade;
	private String bairro;
	
	public Cep() {		
	}
	
	public Cep(String numero, String endereco, String uf, String cidade, String bairro) {
		super();
		this.numero = numero;
		this.endereco = endereco;
		this.uf = uf;
		this.cidade = cidade;
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	
}

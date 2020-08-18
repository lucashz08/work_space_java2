package org.serratec.correios.dominio;

public class Cep {
	
	private String numero;
	private String endereco;
	private String uf;
	
	public Cep() {		
	}
	
	public Cep(String numero, String endereco, String uf) {
		super();
		
		this.numero = numero;
		this.endereco = endereco;
		this.uf = uf;
	}		
	
	public boolean isSimilar(Cep outroCep) {
		return isSimilar(outroCep.getNumero());
	}
	
	public boolean isSimilar(String outroNumero) {
		String outroNumeroTratado = outroNumero.replace("-", "");
		String numeroTratado = this.numero.replace("-", "");
	
		return outroNumeroTratado.equals(numeroTratado);
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
	
	
}

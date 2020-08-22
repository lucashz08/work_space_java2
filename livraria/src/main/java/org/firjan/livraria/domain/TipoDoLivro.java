package org.firjan.livraria.domain;

public enum TipoDoLivro {

	FANTASIA(1),
	TECNICO(2),
	ROMANCE(3),
	DRAMA(4);
	
	public int id;
	
	TipoDoLivro(int id) {
		this.id = id;
	}
	
}

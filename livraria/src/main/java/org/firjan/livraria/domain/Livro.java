package org.firjan.livraria.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Livro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	
	private TipoDoLivro tipo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
				name = "autor_livro",
				joinColumns = @JoinColumn(name = "livro_id", referencedColumnName =  "id"),
				inverseJoinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id")
	)
	private List<Autor> autores;
	
	@OneToOne
	private Editora editora;	
	
	private LocalDate dataPublicacao;

	public Livro() {
		
	}
	
	public Livro(Editora editora, TipoDoLivro tipo, String titulo, LocalDate dataPublicacao) {
		super();		
		
		this.autores = new ArrayList<>();
		this.editora = editora;
		this.tipo = tipo;
		this.titulo = titulo;
		this.dataPublicacao = dataPublicacao;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoDoLivro getTipo() {
		return tipo;
	}

	public void setTipo(TipoDoLivro tipo) {
		this.tipo = tipo;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	
	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

}

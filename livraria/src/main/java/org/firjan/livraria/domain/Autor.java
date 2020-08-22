package org.firjan.livraria.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Autor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min = 10, max = 10)
	@Column(unique = true)
	private String codigo;
	
	@NotNull
	@Size(min = 10, max = 40)	
	private String nome;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
				name = "autor_livro",
				joinColumns = @JoinColumn(name = "autor_id", referencedColumnName =  "id"),
				inverseJoinColumns = @JoinColumn(name = "livro_id", referencedColumnName = "id")
	)
	@JsonIgnore
	private List<Livro> livros;	

	public Autor() {		
	}
	
	public Autor(String codigo, String nome) {
		super();		
		
		this.codigo = codigo;
		this.nome = nome;		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
}

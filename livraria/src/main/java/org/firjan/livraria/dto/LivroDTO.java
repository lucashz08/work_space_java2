package org.firjan.livraria.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.firjan.livraria.domain.Autor;
import org.firjan.livraria.domain.Editora;
import org.firjan.livraria.domain.Livro;
import org.firjan.livraria.domain.TipoDoLivro;
import org.firjan.livraria.repository.AutorRepository;
import org.firjan.livraria.repository.EditoraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class LivroDTO {
	
	@NotNull
	@Size(min=5, max=30)
	private String titulo;
	
	@NotNull
	private TipoDoLivro tipo;
	
	@NotNull
	private Set<String> autores;
	
	@NotNull
	private String editora;
	
	@NotNull
	@Past
	private LocalDate dataPublicacao;
	
	public Livro toLivro(AutorRepository autorRepository, EditoraRepository editoraRepository) {
		
		Editora editora = editoraRepository.findByCodigo(this.editora);
		
		if (editora == null) {	
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "A editora não existe");
		}
		
		Livro livro = new Livro(editora, this.tipo, this.titulo, this.dataPublicacao);
		
		for (String a : this.autores) {
			Autor autor = autorRepository.findByCodigo(a);
			
			if (autor == null) {	
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "O autor não existe");
			}	
			
			livro.getAutores().add(autor);
		}
		
		if (livro.getAutores().size() == 0) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "O livro não possui autores");
		}
		
		return livro;
	}
	
	public static LivroDTO fromLivro(Livro livro) {
		LivroDTO dto = new LivroDTO();
		dto.setTitulo(livro.getTitulo());
		dto.setEditora(livro.getEditora().getNome());
		dto.setTipo(livro.getTipo());
		dto.setDataPublicacao(livro.getDataPublicacao());
		
		dto.autores = new HashSet<>();
		
		for (Autor a : livro.getAutores()) {
			dto.getAutores().add(a.getNome());
		}
		
		return dto;
	}
	
	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
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

	public Set<String> getAutores() {
		return autores;
	}

	public void setAutor(Set<String> autores) {
		this.autores = autores;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
}

package org.firjan.livraria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.firjan.livraria.domain.Livro;
import org.firjan.livraria.dto.LivroDTO;
import org.firjan.livraria.repository.AutorRepository;
import org.firjan.livraria.repository.EditoraRepository;
import org.firjan.livraria.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private EditoraRepository editoraRepository;
	
	@GetMapping
	public ResponseEntity<List<LivroDTO>> getLivros(@RequestParam Map<String,String> parametros) {
					
		List<Livro> todos = livroRepository.findAll();
		List<LivroDTO> todosDto = new ArrayList<>();
		
		for (Livro livro : todos) {
			todosDto.add(LivroDTO.fromLivro(livro));
		}
		
		return ResponseEntity.ok().body(todosDto);
	}	
	
	@PostMapping
	public ResponseEntity<?> postLivro(@Valid @RequestBody LivroDTO dto) {

		try {
			Livro livro = dto.toLivro(autorRepository, editoraRepository);
		
			livroRepository.save(livro);
			
			return ResponseEntity.ok().build();
			
		} catch (HttpServerErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> deleteLivro(@PathVariable Integer id) {
		
		Livro existente = livroRepository.findById(id).orElse(null);
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		livroRepository.delete(existente);
		
		return ResponseEntity.ok().body(existente);
	}
}

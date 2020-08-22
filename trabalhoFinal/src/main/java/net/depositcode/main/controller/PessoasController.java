package net.depositcode.main.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.depositcode.main.domain.Pessoas;
import net.depositcode.main.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {

	@Autowired
	private PessoaRepository repository;

	@GetMapping
	public ResponseEntity<List<Pessoas>> getAll() {

		List<Pessoas> pessoasTodos = repository.findAll();

		return ResponseEntity.ok(pessoasTodos);
	}

	@PostMapping
	public ResponseEntity<Pessoas> postPessoa(@RequestBody Pessoas novo) {

		Pessoas existente = repository.findByCpf(novo.getCpf());

		if (existente != null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);

		novo.setId(null);
		repository.save(novo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novo.getId()).toUri();
		
		return  ResponseEntity.created(uri).build(); // forma correta de insert dados. 
		
		//return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Pessoas> putPessoa(@RequestBody Pessoas modificado) {

		Pessoas existente = repository.findByCpf(modificado.getCpf());

		if (existente == null)
			return ResponseEntity.notFound().build();

		existente.setDataNascimento(modificado.getDataNascimento());
		existente.setNome(modificado.getNome());

		repository.save(modificado);

		return ResponseEntity.ok().body(modificado);

	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Pessoas> deletePessoa(@PathVariable String codigo) {

		Pessoas existente = (repository.findByCpf(codigo) != null) ? repository.findByCpf(codigo) : repository.findById(Integer.valueOf(codigo));

		if (existente == null)
			return ResponseEntity.notFound().build();

		repository.delete(existente);

		return ResponseEntity.ok().body(existente);

	}
}

package net.depositcode.main.controller;

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

import net.depositcode.main.dominio.PessoaOuEmpresa;
import net.depositcode.main.repository.PessoaOuEmpresaRepository;

@RestController
@RequestMapping("/cpf")
public class CpfOfCnpjController {

	@Autowired
	PessoaOuEmpresaRepository repository;

	public CpfOfCnpjController() {

	}

	@GetMapping("")
	public ResponseEntity<List<PessoaOuEmpresa>> getTodos() {
		List<PessoaOuEmpresa> lista = (List<PessoaOuEmpresa>) repository.findAll();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/{codigoCpf}")
	public ResponseEntity<PessoaOuEmpresa> getPessoaOuEmpresa(@PathVariable String codigoCpf) {

		PessoaOuEmpresa encontra = repository.findByPessoaEmpresaId(codigoCpf);
		if (encontra != null)
			return new ResponseEntity<>(encontra, HttpStatus.ACCEPTED);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<PessoaOuEmpresa> postPessoaOuEmpresa(@RequestBody PessoaOuEmpresa p) {

		PessoaOuEmpresa pessoaEmpresa = repository.findByPessoaEmpresaId(p.getPessoaEmpresaId());
		if (pessoaEmpresa != null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);

		repository.save(p);
		System.out.println(p);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("{pessoaEmpresaId}")
	public ResponseEntity<PessoaOuEmpresa> deletePessoaOuEmpresa(@PathVariable String pessoaEmpresaId) {

		PessoaOuEmpresa p = repository.findByPessoaEmpresaId(pessoaEmpresaId);

		if (p == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		repository.delete(p);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@PutMapping
	public ResponseEntity<PessoaOuEmpresa> putPessoaOuEmpresa(@RequestBody PessoaOuEmpresa p) {

		PessoaOuEmpresa pe = repository.findByPessoaEmpresaId(p.getPessoaEmpresaId());

		if (pe == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		pe.setNome(p.getNome());
		pe.setPfOrPj(p.getPfOrPj());
		repository.save(pe);

		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}

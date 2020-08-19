package org.serratec.correios.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.serratec.correios.dominio.Cep;
import org.serratec.correios.repositorio.CepRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {

	@Autowired
	private CepRepository repository;

	public CepController() {
	}

	@GetMapping("")
	public List<Cep> getTodos(@RequestParam Map<String, String> parametros) { // pega o valores por query string ex:
																				// ?cpf=25957005
		List<Cep> todos = (List<Cep>) repository.findAll();
		List<Cep> filtrado = new ArrayList<>();

		for (Cep cep : todos) {

			if (!parametros.getOrDefault("endereco", cep.getEndereco()).equals(cep.getEndereco())) {
				continue;
			}

			if (!parametros.getOrDefault("uf", cep.getUf()).equals(cep.getUf())) {
				continue;
			}

			if (!parametros.getOrDefault("cep", cep.getNumero()).equals(cep.getNumero())) {
				continue;
			}

			if (!parametros.getOrDefault("cidade", cep.getCidade()).equals(cep.getCidade()))
				continue;

			if (!parametros.getOrDefault("bairro", cep.getBairro()).equals(cep.getBairro()))

				filtrado.add(cep);
		}

		return filtrado;
	}
	
	@GetMapping("/")
	public ResponseEntity<Cep> getCep()
	{
		return new ResponseEntity<Cep>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{numero}")
	public ResponseEntity<Cep> getCep(@PathVariable String numero) { // pega o valor passado por depois da /

		Cep cep = repository.findByNumero(numero);

		if (cep != null)
			return new ResponseEntity<>(cep, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/uf/{uf}")
	public ResponseEntity<List<Cep>> getUf(@PathVariable String uf){
		
		List<Cep> lista = repository.findAllByUf(uf);
		
		if(lista.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cep> postCep(@RequestBody Cep novo) {
		
		Cep existente = repository.findByNumero(novo.getNumero());
		
		if(existente != null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);

		repository.save(novo);

		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}

	@PutMapping("/{numero}")
	public ResponseEntity<Cep> putCep(@PathVariable String numero, @RequestBody Cep modificado) {
	
		Cep existente = repository.findByNumero(numero);
		
		if (existente != null) {
			
			existente.setEndereco(modificado.getEndereco());
			existente.setUf(modificado.getUf());
			existente.setBairro(modificado.getBairro());
			existente.setCidade(modificado.getCidade());
			//existente.setNumero(modificado.getNumero());
			
			repository.save(existente);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);

		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{numero}")
	public ResponseEntity<Cep> deleteCep(@PathVariable String numero) {
		
		Cep ex = repository.findByNumero(numero);
		
		if(ex != null) {

			repository.delete(ex);
			return new ResponseEntity<>(ex, HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}

package org.serratec.correios.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.serratec.correios.dominio.Cep;
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

	private List<Cep> todos = new ArrayList<>();

	public CepController() {
		todos.add(new Cep("25954-000", "Rua Tenente Luis Meirelles", "RJ"));
		todos.add(new Cep("25955-120", "Rua Santa Filomena", "RJ"));
		todos.add(new Cep("25970-600", "Estrada Novo Circuito", "RJ"));
		todos.add(new Cep("25970-490", "Estrada da Gruta", "RJ"));
		todos.add(new Cep("25965-176", "Rua Doutor Oliveira", "RJ"));
	}

	@GetMapping("")
	public List<Cep> getTodos(@RequestParam Map<String, String> parametros) { // pega o valores por query string ex: ?cpf=25957005
		
		List<Cep> filtrado = new ArrayList<>();
		
		for(Cep cep : todos) {
			if(!parametros.getOrDefault("endereco", cep.getEndereco()).equals(cep.getEndereco())) {
				continue;
			}
			if(!parametros.getOrDefault("uf", cep.getUf()).equals(cep.getUf())){
				continue;
			}
			if(!parametros.getOrDefault("cep", cep.getNumero()).equals(cep.getNumero())) {
				continue;
			}
			filtrado.add(cep);
		}
		
		return filtrado;
	}

	@GetMapping("/{numero}")
	public ResponseEntity<Cep> getCep(@PathVariable String numero) { // pega o valor passado por depois da /

		for (Cep cep : todos) {
			if (cep.isSimilar(numero)) {
				return new ResponseEntity<>(cep, HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Cep> postCep(@RequestBody Cep novo) {
		for (Cep cep : todos) {
			if (cep.isSimilar(novo)) {
				return new ResponseEntity<>(cep, HttpStatus.FORBIDDEN);
			}

			if (novo.getEndereco().trim().equals("") || novo.getNumero().trim().equals(""))
				return new ResponseEntity<>(novo, HttpStatus.FORBIDDEN);
		}

		todos.add(novo);

		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}

	@PutMapping("/{numero}")
	public ResponseEntity<Cep> putCep(@PathVariable String numero, @RequestBody Cep modificado) {
		for (Cep cep : todos) {
			if (cep.isSimilar(numero)) {
				cep.setEndereco(modificado.getEndereco());
				return new ResponseEntity<>(modificado, HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{numero}")
	public ResponseEntity<Cep> deleteCep(@PathVariable String numero) {
		for (Cep cep : todos) {
			if (cep.isSimilar(numero)) {
				todos.remove(cep);

				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("")
	public ResponseEntity<Cep> deleteTodos() {
		todos.clear();

		return new ResponseEntity<>(HttpStatus.OK);
	}
}

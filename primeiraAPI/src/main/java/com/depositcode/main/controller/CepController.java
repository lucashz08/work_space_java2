package com.depositcode.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.depositcode.main.dominio.Cep;

@RestController
@RequestMapping("/cep")
public class CepController {

	private  List<Cep> todos;
	
	public CepController() {
		
		todos = new ArrayList<Cep>();
		todos.add(new Cep("25954000", "Rua Tenente Luis"));
		todos.add(new Cep("25955120", "Rua Santa"));
		todos.add(new Cep("25965176", "Rua Doutor Oliveira"));
		
	}
	
	@GetMapping("")
	public List<Cep> getTodos(){
		return todos;
	}
	
	@GetMapping("/{numero}")
	public ResponseEntity<Cep> getCep(@PathVariable String numero) {
		
		for (Cep cep : todos) {
			if(cep.getNumero().equals(numero)) {
				return new ResponseEntity<>(cep, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}

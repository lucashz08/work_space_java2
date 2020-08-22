package org.firjan.livraria.config;

import java.util.ArrayList;
import java.util.List;

import org.firjan.livraria.domain.Autor;
import org.firjan.livraria.domain.Editora;
import org.firjan.livraria.repository.AutorRepository;
import org.firjan.livraria.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private EditoraRepository editoraRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	public void run(ApplicationArguments args) {
	
		if (editoraRepository.count() == 0) {
			List<Editora> editoras = new ArrayList<>();
			editoras.add(new Editora("00001", "Editora de Teresopolis"));
			editoras.add(new Editora("00002", "Editora de Petropolis"));
			editoras.add(new Editora("00003", "Editora de Nova Friburgo"));
			
			editoraRepository.saveAll(editoras);	
		}		
		
		if (autorRepository.count() == 0) {
			List<Autor> autores = new ArrayList<>();
			autores.add(new Autor("RJ00000001", "Francisco Soares de Souza Neto"));
			autores.add(new Autor("RJ00000002", "Camila Amaral Batista"));
			autores.add(new Autor("RJ00000003", "Kallebe Pessoa"));
			
			autorRepository.saveAll(autores);
		}
	}	
}
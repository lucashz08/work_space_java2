package org.firjan.livraria.repository;

import java.util.List;

import org.firjan.livraria.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, String> {

	Autor findByCodigo(String codigo);
	
	List<Autor> findByNome(String nome);
}

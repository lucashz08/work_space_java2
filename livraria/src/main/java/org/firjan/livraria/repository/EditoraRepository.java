package org.firjan.livraria.repository;

import org.firjan.livraria.domain.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, String> {

	Editora findByCodigo(String codigo);
	Boolean existsByCodigo(String codigo);
}

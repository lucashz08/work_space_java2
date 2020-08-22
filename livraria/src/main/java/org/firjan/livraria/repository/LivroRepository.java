package org.firjan.livraria.repository;

import org.firjan.livraria.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {


}

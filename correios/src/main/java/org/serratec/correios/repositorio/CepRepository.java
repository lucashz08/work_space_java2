package org.serratec.correios.repositorio;

import java.util.List;

import org.serratec.correios.dominio.Cep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CepRepository extends JpaRepository<Cep, String>
{
	
	Cep findByNumero(String numero);
	List<Cep> findAllByUf(String uf);

}

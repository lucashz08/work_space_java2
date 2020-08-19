package org.serratec.correios.repositorio;

import org.serratec.correios.dominio.Cep;
import org.springframework.data.repository.CrudRepository;

public interface CepRepository extends CrudRepository<Cep, String>
{
	
	Cep findByNumero(String numero);

}

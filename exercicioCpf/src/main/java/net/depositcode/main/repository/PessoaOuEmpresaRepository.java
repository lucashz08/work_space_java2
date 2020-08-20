package net.depositcode.main.repository;

import org.springframework.data.repository.CrudRepository;

import net.depositcode.main.dominio.PessoaOuEmpresa;

public interface PessoaOuEmpresaRepository extends CrudRepository<PessoaOuEmpresa,String>{
	
	PessoaOuEmpresa findByPessoaEmpresaId(String pessoaEmpresaId);

}

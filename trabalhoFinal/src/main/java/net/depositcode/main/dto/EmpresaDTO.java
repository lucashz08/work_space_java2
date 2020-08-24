package net.depositcode.main.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import net.depositcode.main.domain.Empresa;
import net.depositcode.main.domain.Pessoas;

public class EmpresaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nomeFantasia;
	private String cnpj;
	private LocalDate dataCriacao;
	private List<PessoasDTO> pessoas;
	
	public EmpresaDTO() {
	}
	
	public EmpresaDTO(Empresa empresa) {

		this.nomeFantasia = empresa.getNomeFantasia();
		this.cnpj = empresa.getCnpj();
		this.dataCriacao = empresa.getDataCriacao();
		List<Pessoas> p = empresa.getPessoas();
		this.pessoas =  p.stream().map(obj -> new PessoasDTO(obj)).collect(Collectors.toList());
	}
	
	public EmpresaDTO(String nomeFantasia, String cnpj, LocalDate dataCriacao) {
		super();
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.dataCriacao = dataCriacao;
	}
	
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public List<PessoasDTO> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoasDTO> pessoas) {
		this.pessoas = pessoas;
	}
	
	

}

package net.depositcode.main.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import net.depositcode.main.domain.Pessoas;
import net.depositcode.main.repository.EmpresaRepository;

public class PessoasDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String cpf;
	private LocalDate dataNascimento;

	@Autowired
	EmpresaRepository empresaRepository;

	public PessoasDTO() {

	}

	public PessoasDTO(Pessoas pessoas) {

		this.nome = pessoas.getNome();
		this.cpf = pessoas.getCpf();
		this.dataNascimento = pessoas.getDataNascimento();

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}

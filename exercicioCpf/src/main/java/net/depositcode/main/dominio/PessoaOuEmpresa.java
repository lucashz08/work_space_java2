package net.depositcode.main.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PESSOAS_EMPRESAS")
public class PessoaOuEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", columnDefinition = "bigint")  // define o tipo de campo no caso Big Int
	@GeneratedValue(strategy = GenerationType.IDENTITY) // define que o id e auto_increment
	
	private Integer id;

	@Column(name = "NAME", length = 50)
	private String nome;

	@Column(name = "CPF_OU_CNPJ", length = 14, unique = true)
	private String pessoaEmpresaId;

	@Column(name = "PESSOA_FISICA_OU_PESSOA_JURIDICA", length = 2)
	private String pfOrPj;

	public PessoaOuEmpresa() {

	}

	public PessoaOuEmpresa(Integer id, String nome, String pessoaEmpresaId, String pfOrPj) {
		super();
		this.id = id;
		this.nome = nome;
		this.pessoaEmpresaId = pessoaEmpresaId;
		this.pfOrPj = pfOrPj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPessoaEmpresaId() {
		return pessoaEmpresaId;
	}

	public void setPessoaEmpresaId(String pessoaEmpresaId) {
		this.pessoaEmpresaId = pessoaEmpresaId;
	}

	public String getPfOrPj() {
		return pfOrPj;
	}

	public void setPfOrPj(String pfOrPj) {
		this.pfOrPj = pfOrPj;
	}

	@Override
	public String toString() {
		return "PessoaOuEmpresa [id=" + id + ", nome=" + nome + ", pessoaEmpresaId=" + pessoaEmpresaId + ", pfOrPj="
				+ pfOrPj + "]";
	}

}

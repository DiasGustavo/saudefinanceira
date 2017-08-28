package br.com.saudefinanceira.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_pessoa_juridica")
@NamedQueries({
	@NamedQuery(name = "PessoaJuridica.listar", query = "SELECT pjuridica FROM PessoaJuridica pjuridica"),
	@NamedQuery(name = "PessoaJuridica.buscarPorCodigo", query = "SELECT pjuridica FROM PessoaJuridica pjuridica WHERE pjuridica.id = :codigo")
})
public class PessoaJuridica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa_juridica")
	private Long id;
	@NotEmpty(message = "o campo CNPJ é obrigatório")
	@Size(min=1, max=18, message="o CNPJ deve ter entre 1 e 18 caracteres")
	@Column(name = "cnpj", length = 18, nullable = false)
	private String cnpj;
	
	@NotEmpty(message = "o campo razão social é obrigatório")
	@Size(min=1, max=50, message="a razão social deve ter entre 1 e 50 caracteres")
	@Column(name = "razao_social", length = 50, nullable = false)
	private String razaoSocial;
	
	@Column(name = "suframa", length = 9)
	private String suframa;
	
	@Column(name = "insc_estadual", length = 15)
	private String inscricaoEstadual;
	
	@NotEmpty(message = "o campo email é obrigatório")
	@Email(message = "o email informado é inválido")
	@Column(name = "email", length = 50)
	private String email;
	
	@Column(name = "telefone", length = 13)
	private String telefone;
	
	@Column(name = "celular", length = 13)
	private String celular;
	
	@Column(name = "observacao", length = 50)
	private String observacao;
	
	@NotNull(message = "o campo pessoa é obrigatório")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_pessoa", referencedColumnName = "id_pessoa", nullable = false)
	private Pessoa pessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getSuframa() {
		return suframa;
	}

	public void setSuframa(String suframa) {
		this.suframa = suframa;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "PessoaJuridica [id=" + id + ", cnpj=" + cnpj + ", razaoSocial=" + razaoSocial + ", suframa=" + suframa
				+ ", inscricaoEstadual=" + inscricaoEstadual + ", email=" + email + ", telefone=" + telefone
				+ ", celular=" + celular + ", observacao=" + observacao + ", pessoa=" + pessoa + "]";
	}	
}

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

@Entity
@Table(name = "tbl_usuario")
@NamedQueries({
	@NamedQuery(name = "Usuarios.listar", query = "SELECT usuario FROM Usuarios usuario"),
	@NamedQuery(name = "Usuarios.buscarPorCodigo", query = "SELECT usuario FROM Usuarios usuario WHERE usuario.id = :codigo")
})
public class Usuarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name = "login", length = 50, nullable = false)
	private String login;
	
	@Column(name = "senha", length = 50, nullable = false)
	private String senha;
	
	@Column(name = "perfil", length = 50, nullable = false)
	private String perfil;
	
	@Column(name = "status", length = 1, nullable = false)
	private String status;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_pessoa_fisica", referencedColumnName = "id_pessoa_fisica", nullable = false)
	private PessoaFisica pfisica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PessoaFisica getPfisica() {
		return pfisica;
	}

	public void setPfisica(PessoaFisica pfisica) {
		this.pfisica = pfisica;
	}

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", login=" + login + ", senha=" + senha + ", perfil=" + perfil + ", status="
				+ status + ", pfisica=" + pfisica + "]";
	}
	
}

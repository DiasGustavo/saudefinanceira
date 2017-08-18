package br.com.saudefinanceira.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_endereco")
@NamedQueries({ 
	@NamedQuery(name = "Endereco.listar", query = "SELECT endereco FROM Endereco endereco"),
	@NamedQuery(name = "Endereco.buscarPorCodigo", query = "SELECT endereco FROM Endereco endereco WHERE endereco.id = :codigo")
})
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_endereco")
	private Long id;

	@Column(name = "rua", length = 50, nullable = false)
	private String logradouro;

	@Column(name = "cidade", length = 50, nullable = false)
	private String cidade;

	@Column(name = "estado", length = 50, nullable = false)
	private String estado;

	@Column(name = "bairro", length = 50, nullable = false)
	private String bairro;

	@Column(name = "complemento", length = 50)
	private String complemento;

	@Column(name = "numero", length = 50)
	private String numero;

	@Column(name = "cep", length = 9)
	private String cep;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", cidade=" + cidade + ", estado=" + estado
				+ ", bairro=" + bairro + ", complemento=" + complemento + ", numero=" + numero + ", cep=" + cep + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}

package br.com.saudefinanceira.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "tbl_ccusto")
@NamedQueries({
	@NamedQuery(name = "CentroCusto.listar", query = "SELECT centro FROM CentroCusto centro"),
	@NamedQuery(name = "CentroCusto.buscarPorCodigo", query = "SELECT centro FROM CentroCusto centro WHERE centro.id = :codigo")
})
public class CentroCusto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ccusto")
	private Long id;
	
	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;
	
	@Column(name = "status", length = 50, nullable = false)
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_pjuridica", referencedColumnName = "id_pessoa_juridica", nullable = false)
	private PessoaJuridica pjuridica;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PessoaJuridica getPjuridica() {
		return pjuridica;
	}

	public void setPjuridica(PessoaJuridica pjuridica) {
		this.pjuridica = pjuridica;
	}

	@Override
	public String toString() {
		return "CentroCusto [id=" + id + ", descricao=" + descricao + ", status=" + status + ", pjuridica=" + pjuridica
				+ "]";
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
		CentroCusto other = (CentroCusto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

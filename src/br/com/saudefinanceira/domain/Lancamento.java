package br.com.saudefinanceira.domain;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_lancamento")
@NamedQueries({
	@NamedQuery(name = "Lancamento.listar", query = "SELECT lancamento FROM Lancamento lancamento"),
	@NamedQuery(name = "Lancamento.buscarPorCodigo", query = "SELECT lancamento FROM Lancamento lancamento WHERE lancamento.id = :codigo")
})
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_lancamento")
	private Long id;
	
	@NotEmpty(message = "o campo descrição é obrigatório")
	@Column(name = "descricao", length = 50)
	private String descricao;
	
	@NotNull(message="o campo valor total é obrigatório.")
	@DecimalMin(value="0.00", message="o campo valor total deve ser maior do que 0.00")
	@Digits(integer = 5, fraction = 2, message = "coloque um valor válido para o valor total")
	@Column(name = "valor_total", precision = 7, scale = 2, nullable = false)
	private BigDecimal total;
	
	@NotEmpty(message = "o campo condição de pagamento é obrigatório")
	@Size(min=1, max=20, message = "a condição de pagamento deve ter entre 1 e 20 caracteres")
	@Column(name = "condicao_pagamento", length = 20)
	private String condicaoPagamento;
	
	@NotNull(message = "o campo data de lançamento é obrigatório")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_lancamento", nullable = false)
	private Date dataLancamento;
	
	@NotNull(message = "o campo data de vencimento é obrigatório")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dt_vencimento", nullable = false)
	private Date dataVencimento;

	@NotNull(message = "O campo pessoa é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_pessoa", referencedColumnName = "id_pessoa", nullable = false)
	private Pessoa pessoa;

	

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



	public BigDecimal getTotal() {
		return total;
	}



	public void setTotal(BigDecimal total) {
		this.total = total;
	}



	public String getCondicaoPagamento() {
		return condicaoPagamento;
	}



	public void setCondicaoPagamento(String condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}



	public Date getDataLancamento() {
		return dataLancamento;
	}



	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}



	public Date getDataVencimento() {
		return dataVencimento;
	}



	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}



	public Pessoa getPessoa() {
		return pessoa;
	}



	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}



	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", descricao=" + descricao + ", total=" + total + ", pessoa=" + pessoa + "]";
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
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
}

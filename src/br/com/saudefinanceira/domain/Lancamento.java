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
	
	@Column(name = "descricao", length = 50)
	private String descricao;
	
	@Column(name = "valor_total", precision = 7, scale = 2, nullable = false)
	private BigDecimal total;
	
	@Column(name = "condicao_pagamento", length = 20)
	private String condicaoPagamento;
	
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_lancamento", nullable = false)
	private Date dataLancamento;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dt_vencimento", nullable = false)
	private Date dataVencimento;

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
}

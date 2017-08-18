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
@Table(name = "tbl_despesa")
@NamedQueries({
	@NamedQuery(name = "Despesa.listar", query = "SELECT despesa FROM Despesa despesa"),
	@NamedQuery(name = "Despesa.buscarPorCodigo", query = "SELECT despesa FROM Despesa despesa WHERE despesa.id = :codigo")
})
public class Despesa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_despesa")
	private Long id;
	
	@Column(name = "categoria", length = 20)
	private String categoria;
	
	@Column(name = "descricao", length = 50)
	private String descricao;
	
	@Column(name = "qtde")
	private Integer quantidade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_lancamento", referencedColumnName = "id_lancamento")
	private Lancamento lancamento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_item", referencedColumnName = "id_item")
	private Item item;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Despesa [id=" + id + ", categoria=" + categoria + ", descricao=" + descricao + ", quantidade="
				+ quantidade + ", lancamento=" + lancamento + ", item=" + item + "]";
	}	
}

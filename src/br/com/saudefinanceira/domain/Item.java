package br.com.saudefinanceira.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_item")
@NamedQueries({
	@NamedQuery(name = "Item.listar", query ="SELECT item FROM Item item"),
	@NamedQuery(name = "Item.buscarPorCodigo", query = "SELECT item FROM Item item WHERE item.id = :codigo")
})
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_item")
	private Long id;
	
	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;
	
	@Column(name = "valor", precision = 7, scale = 2, nullable = false)
	private BigDecimal valor;

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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", descricao=" + descricao + ", valor=" + valor + "]";
	}
}

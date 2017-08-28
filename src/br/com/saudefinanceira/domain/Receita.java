package br.com.saudefinanceira.domain;

import java.math.BigDecimal;

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
@Table(name = "tbl_receita")
@NamedQueries({
	@NamedQuery(name = "Receita.listar", query = "SELECT receita FROM Receita receita"),
	@NamedQuery(name = "Receita.buscarPorCodigo", query = "SELECT receita FROM Receita receita WHERE receita.id = :codigo")
})
public class Receita {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "id_receita")
	private Long id;
			
	@Column(name = "valor_unitario", precision = 7, scale = 2, nullable = false)
	private BigDecimal valorUnitario;
	
	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;
	
	@Column(name = "quantidade")
	private Integer quantidade;
		
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "fk_lancamento", referencedColumnName = "id_lancamento")
	private Lancamento lancamento;
	
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "fk_item", referencedColumnName = "id_item")
	private Item item;

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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
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
		return "Receita [id=" + id + ", valorUnitario=" + valorUnitario + ", descricao=" + descricao + ", quantidade="
				+ quantidade + ", lancamento=" + lancamento + ", item=" + item + "]";
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
		Receita other = (Receita) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}		
}

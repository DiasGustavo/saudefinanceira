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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

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
	
	@NotEmpty(message = "O campo descri��o � obrigat�rio")
	@Size(min=1, max=50, message = "A descri��o deve ser ter entre 1 e 50 caracteres")
	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;
	
	@NotNull(message="o campo valor � obrigatorio.")
	@DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
	@Digits(integer = 5, fraction = 2, message = "coloque um valor v�lido")
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

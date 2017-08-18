package br.com.saudefinanceira.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.saudefinanceira.dao.EnderecoDAO;
import br.com.saudefinanceira.domain.Endereco;

@FacesConverter("enderecoConverter")
public class EnderecoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componet, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			
			EnderecoDAO edao = new EnderecoDAO();
			Endereco endereco = edao.buscarPorCodigo(codigo);
			
			return endereco;			
		}catch (RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componet, Object objeto) {
		try{
			Endereco endereco = (Endereco) objeto;
			Long codigo = endereco.getId();
			return codigo.toString();
			
		}catch (RuntimeException ex){
			return null;
		}
	}

}

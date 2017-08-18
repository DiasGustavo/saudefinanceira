package br.com.saudefinanceira.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.saudefinanceira.dao.PessoaDAO;
import br.com.saudefinanceira.domain.Pessoa;

@FacesConverter("pessoaConverter")
public class PessoaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componet, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			
			PessoaDAO pdao = new PessoaDAO();
			Pessoa pessoa = pdao.buscarPorCodigo(codigo);
			
			return pessoa;
			
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componet, Object objeto) {
		try{
			Pessoa pessoa = (Pessoa) objeto;
			Long codigo = pessoa.getId();			
			return codigo.toString();
			
		}catch(RuntimeException ex){
			return null;
		}
	}

}

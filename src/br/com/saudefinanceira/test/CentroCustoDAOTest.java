package br.com.saudefinanceira.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.saudefinanceira.dao.CentroCustoDAO;
import br.com.saudefinanceira.dao.PessoaJuridicaDAO;
import br.com.saudefinanceira.domain.CentroCusto;
import br.com.saudefinanceira.domain.PessoaJuridica;

public class CentroCustoDAOTest {

	@Test
	@Ignore
	public void salvar(){
		CentroCustoDAO cdao = new CentroCustoDAO();
		CentroCusto centro = new CentroCusto();
		centro.setDescricao("Geral");
		centro.setStatus("A");

		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
		PessoaJuridica pjuridica =  pjdao.buscarPorCodigo(1L);
		
		centro.setPjuridica(pjuridica);
		
		cdao.salvar(centro);
	}
	@Test
	@Ignore
	public void listar(){
		CentroCustoDAO cdao = new CentroCustoDAO();
		List<CentroCusto> centroCustos = cdao.listar();
		
		for (CentroCusto centro : centroCustos){
			System.out.println(centro);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		CentroCustoDAO cdao = new CentroCustoDAO();
		CentroCusto centro = new CentroCusto();
		
		centro = cdao.buscarPorCodigo(1L);
		System.out.println(centro);
	}
	
	@Test
	@Ignore
	public void editar(){
		CentroCustoDAO cdao = new CentroCustoDAO();
		CentroCusto centro = cdao.buscarPorCodigo(1L);
		
		centro.setDescricao("Descrição inicial");
		
		cdao.editar(centro);
	}
	
	@Test
	public void excluir(){
		CentroCustoDAO cdao = new CentroCustoDAO();
		CentroCusto centro = cdao.buscarPorCodigo(1L);
		
		cdao.excluir(centro);
	}
	
	
}

package br.com.saudefinanceira.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.saudefinanceira.dao.PessoaDAO;
import br.com.saudefinanceira.dao.PessoaFisicaDAO;
import br.com.saudefinanceira.domain.Pessoa;
import br.com.saudefinanceira.domain.PessoaFisica;

public class PessoaFisicaDAOTest {

	@Test
	
	public void salvar(){
		PessoaFisica pfisica = new PessoaFisica();
		
		pfisica.setCpf("965.756.772-66");
		pfisica.setRg("123456");
		pfisica.setPai("fulano silva santos");
		pfisica.setMae("beltrana silva santos");
		pfisica.setTelefone("3421-2121");
		pfisica.setCelular("3333-3333");
		pfisica.setEmail("fulbel@gmail.com");
		pfisica.setSexo("M");
		pfisica.setObservacao("pessoa fisica exemplo");
		
		PessoaDAO pdao = new PessoaDAO();
		Pessoa pessoa = pdao.buscarPorCodigo(1L);
		pfisica.setPessoa(pessoa);
		
		PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
		pfdao.salvar(pfisica);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		PessoaFisicaDAO pdao = new PessoaFisicaDAO();
		
		List<PessoaFisica> pfisicas = pdao.listar();
		
		for (PessoaFisica pfisica : pfisicas){
			System.out.println(pfisica);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		PessoaFisicaDAO pdao = new PessoaFisicaDAO();
		
		PessoaFisica pfisica = pdao.buscarPorCodigo(1L);
		
		System.out.println(pfisica);
	}
	
	@Test
	@Ignore
	public void editar(){
		PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
		PessoaFisica pfisica = pfdao.buscarPorCodigo(1L);
		
		pfisica.setMae("benedita");
		
		pfdao.editar(pfisica);
	}
	
	@Test
	@Ignore
	public void excluir(){
		PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
		
		PessoaFisica pfisica = pfdao.buscarPorCodigo(1L);
		
		pfdao.excluir(pfisica);
	}
}

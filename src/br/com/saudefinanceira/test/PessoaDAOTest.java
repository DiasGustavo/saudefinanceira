package br.com.saudefinanceira.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.saudefinanceira.dao.EnderecoDAO;
import br.com.saudefinanceira.dao.PessoaDAO;
import br.com.saudefinanceira.domain.Endereco;
import br.com.saudefinanceira.domain.Pessoa;

public class PessoaDAOTest {
	
	@Test
	
	public void salvar(){
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome("Alex");
		pessoa.setObservacao("usuário padrão.");
		
		EnderecoDAO edao = new EnderecoDAO();
		Endereco endereco = edao.buscarPorCodigo(1L);
		pessoa.setEndereco(endereco);
		
		PessoaDAO pdao = new PessoaDAO();
		pdao.salvar(pessoa);
	}
	
	@Test
	@Ignore
	public void listar(){
		PessoaDAO pdao = new PessoaDAO();
		List<Pessoa> pessoas = pdao.listar();
		
		for(Pessoa pessoa : pessoas){
			System.out.println(pessoa);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		PessoaDAO pdao = new PessoaDAO();
		Pessoa pessoa = pdao.buscarPorCodigo(1L);
		
		System.out.println(pessoa);
	}
	
	@Test
	@Ignore
	public void editar(){
		PessoaDAO pdao = new PessoaDAO();
		Pessoa pessoa = pdao.buscarPorCodigo(1L);
		
		pessoa.setNome("Valdir Pereira");
		pessoa.setObservacao("usuario fixo");
		
		pdao.editar(pessoa);
	}
	
	@Test
	@Ignore
	public void excluir(){
		PessoaDAO pdao = new PessoaDAO();
		
		Pessoa pessoa = pdao.buscarPorCodigo(1L);
		
		pdao.excluir(pessoa);
	}
}

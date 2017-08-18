package br.com.saudefinanceira.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.saudefinanceira.dao.PessoaDAO;
import br.com.saudefinanceira.dao.PessoaJuridicaDAO;
import br.com.saudefinanceira.domain.Pessoa;
import br.com.saudefinanceira.domain.PessoaJuridica;

public class PessoaJuridicaDAOTest {

	@Test
	
	public void salvar(){
		PessoaJuridica pj = new PessoaJuridica();
		pj.setCnpj("56.147.157/0001-07");
		pj.setRazaoSocial("Empresa Padrão");
		pj.setInscricaoEstadual("123456");
		pj.setObservacao("empresa padrão");
		
		PessoaDAO pdao = new PessoaDAO();
		Pessoa pessoa = pdao.buscarPorCodigo(2l);
		
		pj.setPessoa(pessoa);
		
		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
		pjdao.salvar(pj);
	}
	
	@Test
	@Ignore
	public void listar(){
		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
		List<PessoaJuridica> pjuridicas = pjdao.listar();
		
		for (PessoaJuridica pjuridica : pjuridicas){
			System.out.println(pjuridica);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
		PessoaJuridica pjuridica = pjdao.buscarPorCodigo(1L);
		
		System.out.println(pjuridica);
	}
	
	@Test
	@Ignore
	public void editar(){
		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
		PessoaJuridica pjuridica = pjdao.buscarPorCodigo(1L);
		
		pjuridica.setObservacao("Empresa de teste");
		pjdao.editar(pjuridica);
	}
	
	@Test
	@Ignore
	public void excluir(){
		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
		PessoaJuridica pjuridica = pjdao.buscarPorCodigo(1L);
		
		pjdao.excluir(pjuridica);
	}
}

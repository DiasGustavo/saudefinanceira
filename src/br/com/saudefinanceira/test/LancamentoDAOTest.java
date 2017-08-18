package br.com.saudefinanceira.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.saudefinanceira.dao.LancamentoDAO;
import br.com.saudefinanceira.dao.PessoaDAO;
import br.com.saudefinanceira.domain.Lancamento;
import br.com.saudefinanceira.domain.Pessoa;

public class LancamentoDAOTest {
	
	@Test
	
	public void salvar(){
		Lancamento lancamento = new Lancamento();
		
		lancamento.setDescricao("descrição padrão.");
		lancamento.setTotal(new BigDecimal(10.0));
		
		PessoaDAO pdao = new PessoaDAO();
		Pessoa pessoa = pdao.buscarPorCodigo(2L);
		
		lancamento.setPessoa(pessoa);
		
		LancamentoDAO ldao = new LancamentoDAO();
		ldao.salvar(lancamento);
	}
	
	@Test
	@Ignore
	public void listar(){
		LancamentoDAO ldao = new LancamentoDAO();
		List<Lancamento> lancamentos = ldao.listar();
		
		for(Lancamento lancamento : lancamentos){
			System.out.println(lancamento);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		LancamentoDAO ldao = new LancamentoDAO();
		Lancamento lancamento = ldao.buscarPorCodigo(1L);
		
		System.out.println(lancamento);
	}
	
	@Test
	@Ignore
	public void editar(){
		LancamentoDAO ldao = new LancamentoDAO();
		Lancamento lancamento = ldao.buscarPorCodigo(1L);
		
		lancamento.setDescricao("Descricao Exemplo.");
		
		ldao.editar(lancamento);
	}
	
	@Test
	@Ignore
	public void excluir(){
		LancamentoDAO ldao = new LancamentoDAO();
		Lancamento lancamento = ldao.buscarPorCodigo(1L);
		
		ldao.excluir(lancamento);
	}

}

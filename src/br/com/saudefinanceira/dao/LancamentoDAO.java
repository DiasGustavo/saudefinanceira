package br.com.saudefinanceira.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.saudefinanceira.domain.Lancamento;
import br.com.saudefinanceira.util.HibernateUtil;

public class LancamentoDAO {

	public void salvar(Lancamento lancamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Lancamento> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Lancamento> lancamentos = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Lancamento.listar");
			lancamentos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return lancamentos;
	}
	
	public Lancamento buscarPorCodigo(Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Lancamento lancamento = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Lancamento.buscarPorCodigo");
			consulta.setLong("codigo", id);
			lancamento = (Lancamento) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return lancamento;
	}
	
	public void editar(Lancamento lancamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(lancamento);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	public void excluir (Lancamento lancamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(lancamento);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
}

package br.com.saudefinanceira.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.saudefinanceira.domain.Despesa;
import br.com.saudefinanceira.util.HibernateUtil;

public class DespesaDAO {
	
	public void salvar(Despesa despesa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(despesa);
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
	public List<Despesa> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Despesa> despesas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Despesa.listar");
			despesas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally {
			sessao.close();
		}
		return despesas;
	}
	
	public Despesa buscarPorCodigo(Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Despesa despesa = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Despesa.buscarPorCodigo");
			consulta.setLong("codigo", id);
			despesa = (Despesa) consulta.uniqueResult();
		} catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return despesa;
	}
	
	public void editar(Despesa despesa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(despesa);
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
	
	public void excluir (Despesa despesa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(despesa);
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

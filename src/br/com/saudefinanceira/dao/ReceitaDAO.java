package br.com.saudefinanceira.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.saudefinanceira.domain.Receita;
import br.com.saudefinanceira.util.HibernateUtil;

public class ReceitaDAO {
	
	public void salvar(Receita receita){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = sessao.beginTransaction();
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(receita);
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
	public List<Receita> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Receita> receitas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Receita.listar");
			receitas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return receitas;
	}
	
	public Receita buscarPorCodigo(Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Receita receita = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Receita.buscarPorCodigo");
			consulta.setLong("codigo", id);
			receita = (Receita) consulta.uniqueResult();
		}catch (RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return receita;
	}
	
	public void editar(Receita receita){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = sessao.beginTransaction();
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(receita);
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
	
	public void excluir (Receita receita){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = sessao.beginTransaction();
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(receita);
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

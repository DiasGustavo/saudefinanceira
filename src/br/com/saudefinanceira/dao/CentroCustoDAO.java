package br.com.saudefinanceira.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.saudefinanceira.domain.CentroCusto;
import br.com.saudefinanceira.util.HibernateUtil;

public class CentroCustoDAO {

	public void salvar(CentroCusto centro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(centro);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CentroCusto> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<CentroCusto> centroCustos = null;
		
		try{
			Query consulta = sessao.getNamedQuery("CentroCusto.listar");
			centroCustos = consulta.list();
			
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return centroCustos;
	}
	
	public CentroCusto buscarPorCodigo (Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		CentroCusto centro = null;
		
		try{
			Query consulta = sessao.getNamedQuery("CentroCusto.buscarPorCodigo");
			consulta.setLong("codigo", id);
			
			centro = (CentroCusto)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return centro;
	}
	
	public void editar(CentroCusto centro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(centro);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}
	
	public void excluir(CentroCusto centro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(centro);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}
}

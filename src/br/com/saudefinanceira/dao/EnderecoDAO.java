package br.com.saudefinanceira.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.saudefinanceira.domain.Endereco;
import br.com.saudefinanceira.util.HibernateUtil;

public class EnderecoDAO {

	public Long salvar(Endereco endereco) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(endereco);
			transacao.commit();
			
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		} finally{
			sessao.close();
		}
		return codigo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Endereco> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Endereco> enderecos = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Endereco.listar");
			enderecos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		} finally{
			sessao.close();
		}
		return enderecos;
	}
	
	public Endereco buscarPorCodigo(Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Endereco endereco = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Endereco.buscarPorCodigo");
			consulta.setLong("codigo", id);
			
			endereco = (Endereco)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return endereco;
	}
	
	public void editar (Endereco endereco){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.update(endereco);
			transacao.commit();
			
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		} finally{
			sessao.close();
		}
	}
	
	public void excluir(Endereco endereco) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(endereco);
			transacao.commit();
			
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		} finally{
			sessao.close();
		}
		
	}
}

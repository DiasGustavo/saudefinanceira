package br.com.saudefinanceira.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.saudefinanceira.domain.Usuarios;
import br.com.saudefinanceira.util.HibernateUtil;

public class UsuariosDAO {

	public void salvar(Usuarios usuario){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(usuario);
			transacao.commit();
		}catch (RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuarios> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Usuarios> usuarios = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Usuarios.listar");
			usuarios = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return usuarios;
	}
	
	public Usuarios buscarPorCodigo(Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Usuarios usuario = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Usuarios.buscarPorCodigo");
			consulta.setLong("codigo", id);
			usuario = (Usuarios) consulta.uniqueResult();
		} catch(RuntimeException  ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return usuario;
	}
	
	public void editar(Usuarios usuario){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(usuario);
			transacao.commit();
		}catch (RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	public void excluir(Usuarios usuario){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(usuario);
			transacao.commit();
		}catch (RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
}

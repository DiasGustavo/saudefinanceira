package br.com.saudefinanceira.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.saudefinanceira.dao.PessoaFisicaDAO;
import br.com.saudefinanceira.dao.UsuariosDAO;
import br.com.saudefinanceira.domain.PessoaFisica;
import br.com.saudefinanceira.domain.Usuarios;

public class UsuariosDAOTest {

	@Test
	
	public void salvar(){
		Usuarios usuario = new Usuarios();
		
		usuario.setLogin("administrador	");
		usuario.setPerfil("gerente");
		usuario.setSenha("qwe123");
		usuario.setStatus("A");
		
		PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
		PessoaFisica pfisica = pfdao.buscarPorCodigo(1L);
		
		usuario.setPfisica(pfisica);
		
		UsuariosDAO udao = new UsuariosDAO();
		udao.salvar(usuario);
	}
	@Test
	@Ignore
	public void listar(){
		UsuariosDAO udao = new UsuariosDAO();
		List<Usuarios> usuarios = udao.listar();
		
		for(Usuarios usuario : usuarios){
			System.out.println(usuario);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		UsuariosDAO udao = new UsuariosDAO();
		Usuarios usuario = udao.buscarPorCodigo(1L);
		
		System.out.println(usuario);
	}
	
	@Test
	@Ignore
	public void editar(){
		UsuariosDAO udao = new UsuariosDAO();
		Usuarios usuario = udao.buscarPorCodigo(1L);
		
		usuario.setPerfil("Administrador");
		
		udao.editar(usuario);
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		UsuariosDAO udao = new UsuariosDAO();
		Usuarios usuario = udao.buscarPorCodigo(1L);
		
		udao.excluir(usuario);
	}
}

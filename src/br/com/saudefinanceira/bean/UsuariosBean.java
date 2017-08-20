package br.com.saudefinanceira.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.saudefinanceira.dao.PessoaFisicaDAO;
import br.com.saudefinanceira.dao.UsuariosDAO;
import br.com.saudefinanceira.domain.PessoaFisica;
import br.com.saudefinanceira.domain.Usuarios;
import br.com.saudefinanceira.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UsuariosBean {

	private Usuarios usuarioCadastro;
	private List<Usuarios> listaUsuarios;
	private List<Usuarios> listaUsuariosFiltrados;
	private List<PessoaFisica> listaPessoasFisicas;
	
	private String acao;
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Usuarios getUsuarioCadastro() {		
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuarios usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	
	public List<Usuarios> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuarios> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Usuarios> getListaUsuariosFiltrados() {
		return listaUsuariosFiltrados;
	}

	public void setListaUsuariosFiltrados(List<Usuarios> listaUsuariosFiltrados) {
		this.listaUsuariosFiltrados = listaUsuariosFiltrados;
	}
	
	public List<PessoaFisica> getListaPessoasFisicas() {
		return listaPessoasFisicas;
	}

	public void setListaPessoasFisicas(List<PessoaFisica> listaPessoasFisicas) {
		this.listaPessoasFisicas = listaPessoasFisicas;
	}

	public void novo(){
		usuarioCadastro = new Usuarios();
	}

	public void salvar() {
		try {
			UsuariosDAO usuarioDAO = new UsuariosDAO();
			usuarioDAO.salvar(usuarioCadastro);
			
			usuarioCadastro = new Usuarios();
			
			FacesUtil.addMsgInfo("Usuário cadastrado com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o usuario" + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			UsuariosDAO udao = new UsuariosDAO();
			listaUsuarios = udao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar os usuarios " + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			acao = FacesUtil.getParam("useracao");
			
			String valor = FacesUtil.getParam("userId");
			
			if(valor != null){
				Long codigo = Long.parseLong(valor);
				
				UsuariosDAO udao = new UsuariosDAO();
				usuarioCadastro = udao.buscarPorCodigo(codigo);
			}else{
				usuarioCadastro = new Usuarios();
			}
			
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			listaPessoasFisicas = pfdao.listar();
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados dos usuarios" + ex.getMessage());
		}
	}
	
	public void editar(){
		try {
			UsuariosDAO usuarioDAO = new UsuariosDAO();
			usuarioDAO.editar(usuarioCadastro);
			
			usuarioCadastro = new Usuarios();
			
			FacesUtil.addMsgInfo("Usuário cadastrado com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o usuario" + ex.getMessage());
		}
	}
	
	public void excluir(){
		try {
			UsuariosDAO usuarioDAO = new UsuariosDAO();
			usuarioDAO.excluir(usuarioCadastro);
			
			usuarioCadastro = new Usuarios();
			
			FacesUtil.addMsgInfo("Usuário cadastrado com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o usuario" + ex.getMessage());
		}
	}
}

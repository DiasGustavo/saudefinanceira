package br.com.saudefinanceira.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.saudefinanceira.dao.PessoaDAO;
import br.com.saudefinanceira.dao.PessoaFisicaDAO;
import br.com.saudefinanceira.domain.Pessoa;
import br.com.saudefinanceira.domain.PessoaFisica;
import br.com.saudefinanceira.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PessoaFisicaBean {

	private PessoaFisica pfisicaCadastro;
	private List<PessoaFisica> listaPessoasFisicas;
	private List<PessoaFisica> listaPessoasFisicasFiltradas;
	private List<Pessoa> listaPessoas;
	
	private String acao;
	
	
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public PessoaFisica getPfisicaCadastro() {
		return pfisicaCadastro;
	}
	public void setPfisicaCadastro(PessoaFisica pfisicaCadastro) {
		this.pfisicaCadastro = pfisicaCadastro;
	}
	public List<PessoaFisica> getListaPessoasFisicas() {
		return listaPessoasFisicas;
	}
	public void setListaPessoasFisicas(List<PessoaFisica> listaPessoasFisicas) {
		this.listaPessoasFisicas = listaPessoasFisicas;
	}
	public List<PessoaFisica> getListaPessoasFisicasFiltradas() {
		return listaPessoasFisicasFiltradas;
	}
	public void setListaPessoasFisicasFiltradas(List<PessoaFisica> listaPessoasFisicasFiltradas) {
		this.listaPessoasFisicasFiltradas = listaPessoasFisicasFiltradas;
	}
	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}
	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
	
	public void novo(){
		pfisicaCadastro = new PessoaFisica();
	}
	
	public void salvar(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			pfdao.salvar(pfisicaCadastro);
			
			pfisicaCadastro = new PessoaFisica();
			
			FacesUtil.addMsgInfo("Pessoa Fisica cadastrada com sucesso");
		}catch (RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a pessoa" + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			listaPessoasFisicas = pfdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar as pessoas" + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			acao = FacesUtil.getParam("pfacao");
			
			String valor = FacesUtil.getParam("pfId");
			
			if(valor != null){
				Long codigo = Long.parseLong(valor);
				
				PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
				pfisicaCadastro = pfdao.buscarPorCodigo(codigo);
			}else{
				pfisicaCadastro = new PessoaFisica();
			}
			PessoaDAO pdao = new PessoaDAO();
			listaPessoas = pdao.listar();
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados da pessoa" + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			pfdao.editar(pfisicaCadastro);
			
			pfisicaCadastro = new PessoaFisica();
			
			FacesUtil.addMsgInfo("Pessoa Fisica editar com sucesso");
		}catch (RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a pessoa" + ex.getMessage());
		}

	}
	
	public void excluir(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			pfdao.excluir(pfisicaCadastro);
			
			pfisicaCadastro = new PessoaFisica();
			
			FacesUtil.addMsgInfo("Pessoa Fisica excluída com sucesso");
		}catch (RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a pessoa" + ex.getMessage());
		}

	}
}

package br.com.saudefinanceira.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.saudefinanceira.dao.EnderecoDAO;
import br.com.saudefinanceira.dao.PessoaDAO;
import br.com.saudefinanceira.dao.PessoaJuridicaDAO;
import br.com.saudefinanceira.domain.Endereco;
import br.com.saudefinanceira.domain.Pessoa;
import br.com.saudefinanceira.domain.PessoaJuridica;
import br.com.saudefinanceira.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PessoaJuridicaBean {

	private PessoaJuridica pjuridicaCadastro;
	private Pessoa pessoaCadastro;
	private Endereco enderecoCadastro;
	
	private List<PessoaJuridica> listaPessoasJuridicas;
	private List<PessoaJuridica> listaPessoasJuridicasFiltradas;
	private List<Pessoa> listaPessoas;
	
	private String acao;
	private Long codigo;
	
	public PessoaJuridica getPjuridicaCadastro() {
		if(pjuridicaCadastro == null){
			pjuridicaCadastro = new PessoaJuridica();
		}
		return pjuridicaCadastro;
	}
	
	public void setPessoaCadastro(Pessoa pessoaCadastro) {
		this.pessoaCadastro = pessoaCadastro;
	}
	
	public Pessoa getPessoaCadastro() {
		if (pessoaCadastro == null){
			pessoaCadastro = new Pessoa();
		}
		return pessoaCadastro;
	}
	
	public void setEnderecoCadastro(Endereco enderecoCadastro) {
		this.enderecoCadastro = enderecoCadastro;
	}
	
	public Endereco getEnderecoCadastro() {
		if (enderecoCadastro == null){
			enderecoCadastro = new Endereco();
		}
		return enderecoCadastro;
	}
	
	public void setPjuridicaCadastro(PessoaJuridica pjuridicaCadastro) {
		this.pjuridicaCadastro = pjuridicaCadastro;
	}
	public List<PessoaJuridica> getListaPessoasJuridicas() {
		return listaPessoasJuridicas;
	}
	public void setListaPessoasJuridicas(List<PessoaJuridica> listaPessoasJuridicas) {
		this.listaPessoasJuridicas = listaPessoasJuridicas;
	}
	public List<PessoaJuridica> getListaPessoasJuridicasFiltradas() {
		return listaPessoasJuridicasFiltradas;
	}
	public void setListaPessoasJuridicasFiltradas(List<PessoaJuridica> listaPessoasJuridicasFiltradas) {
		this.listaPessoasJuridicasFiltradas = listaPessoasJuridicasFiltradas;
	}
	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}
	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public void novo(){
		pjuridicaCadastro = new PessoaJuridica();
		pessoaCadastro = new Pessoa();
		enderecoCadastro = new Endereco();
	}
	
	public void salvar(){
		try{
			//Gravar o Endereço
			EnderecoDAO edao = new EnderecoDAO();
			Long enderecoCod = edao.salvar(enderecoCadastro);
			//Grava os dados Pessoais
			Endereco enderecoFK = edao.buscarPorCodigo(enderecoCod);
			PessoaDAO pdao = new PessoaDAO();
			pessoaCadastro.setEndereco(enderecoFK);
			Long pessoaCod = pdao.salvar(pessoaCadastro);
			//Grava os dados de pessoa jurídica
			Pessoa pessoFK = pdao.buscarPorCodigo(pessoaCod);			
			pjuridicaCadastro.setPessoa(pessoFK);
			PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
			pjdao.salvar(pjuridicaCadastro);
			
			pjuridicaCadastro = new PessoaJuridica();
			pessoaCadastro = new Pessoa();
			enderecoCadastro = new Endereco();
			
			FacesUtil.addMsgInfo("Pessoa Jurídica cadastrada com sucesso");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a pessoa" + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
			listaPessoasJuridicas = pjdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar as pessoa" + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if (codigo != null){
				PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
				pjuridicaCadastro = pjdao.buscarPorCodigo(codigo);
				
				PessoaDAO pdao = new PessoaDAO();
				pessoaCadastro = pdao.buscarPorCodigo(pjuridicaCadastro.getPessoa().getId());
				
				EnderecoDAO edao = new EnderecoDAO();
				enderecoCadastro = edao.buscarPorCodigo(pjuridicaCadastro.getPessoa().getEndereco().getId());
			}else{
				pjuridicaCadastro = new PessoaJuridica();
				pessoaCadastro = new Pessoa();
				enderecoCadastro = new Endereco();
			}			
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os dados das pessoas" + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
			pjdao.editar(pjuridicaCadastro);
			
			PessoaDAO pdao = new PessoaDAO();
			pdao.editar(pessoaCadastro);
			
			EnderecoDAO edao = new EnderecoDAO();
			edao.editar(enderecoCadastro);
			
			pjuridicaCadastro = new PessoaJuridica();
			pessoaCadastro = new Pessoa();
			enderecoCadastro = new Endereco();
			
			FacesUtil.addMsgInfo("Pessoa Jurídica editada com sucesso");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a pessoa" + ex.getMessage());
		}
	}
	
	public void excluir(){
		try{
			PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
			pjdao.excluir(pjuridicaCadastro);
			
			PessoaDAO pdao = new PessoaDAO();
			pdao.excluir(pessoaCadastro);
			
			EnderecoDAO edao = new EnderecoDAO();
			edao.excluir(enderecoCadastro);
				
			codigo = null;
			
			FacesUtil.addMsgInfo("Pessoa Jurídica excluída com sucesso");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a pessoa" + ex.getMessage());
		}
	}
}

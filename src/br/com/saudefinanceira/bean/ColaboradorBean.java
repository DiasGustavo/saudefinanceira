package br.com.saudefinanceira.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.saudefinanceira.dao.EnderecoDAO;
import br.com.saudefinanceira.dao.PessoaDAO;
import br.com.saudefinanceira.dao.PessoaFisicaDAO;
import br.com.saudefinanceira.domain.Endereco;
import br.com.saudefinanceira.domain.Pessoa;
import br.com.saudefinanceira.domain.PessoaFisica;
import br.com.saudefinanceira.util.FacesUtil;


@ManagedBean
@ViewScoped
public class ColaboradorBean {

	private List<PessoaFisica> listaPFisicas;
	private List<PessoaFisica> listaPFisicasFiltradas;
	
	private Pessoa pessoaCadastro;
	private PessoaFisica pfisicaCadastro;
	private Endereco enderecoCadastro;
	
	private String acao;
	private Long codigo;
	
	public List<PessoaFisica> getListaPFisicasFiltradas() {
		return listaPFisicasFiltradas;
	}
	
	public void setListaPFisicasFiltradas(List<PessoaFisica> listaPFisicasFiltradas) {
		this.listaPFisicasFiltradas = listaPFisicasFiltradas;
	}
	
	public List<PessoaFisica> getListaPFisicas() {
		return listaPFisicas;
	}
	
	public void setListaPFisicas(List<PessoaFisica> listaPFisicas) {
		this.listaPFisicas = listaPFisicas;
	}
	
	public Pessoa getPessoaCadastro() {
		if (pessoaCadastro == null){
			pessoaCadastro = new Pessoa();
		}
		return pessoaCadastro;
	}
	public void setPessoaCadastro(Pessoa pessoaCadastro) {
		this.pessoaCadastro = pessoaCadastro;
	}
	public PessoaFisica getPfisicaCadastro() {
		if (pfisicaCadastro ==  null){
			pfisicaCadastro = new PessoaFisica();
		}
		return pfisicaCadastro;
	}
	public void setPfisicaCadastro(PessoaFisica pfisicaCadastro) {
		this.pfisicaCadastro = pfisicaCadastro;
	}
	public Endereco getEnderecoCadastro() {
		if (enderecoCadastro == null){
			enderecoCadastro = new Endereco();
		}
		return enderecoCadastro;
	}
	public void setEnderecoCadastro(Endereco enderecoCadastro) {
		this.enderecoCadastro = enderecoCadastro;
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
		pfisicaCadastro = new PessoaFisica();
		pessoaCadastro = new Pessoa();
		enderecoCadastro = new Endereco();
	}
	
	public void salvar(){
		try{
			//Grava o Endereço
			EnderecoDAO edao = new EnderecoDAO();
			Long enderecoCod = edao.salvar(enderecoCadastro);
			//Grava os dados Pessoais
			Endereco enderecoFK = edao.buscarPorCodigo(enderecoCod);
			PessoaDAO pdao = new PessoaDAO();
			pessoaCadastro.setEndereco(enderecoFK);
			Long pessoaCod = pdao.salvar(pessoaCadastro);
			//Grava os dados de pessoa Física
			Pessoa pessoaFK = pdao.buscarPorCodigo(pessoaCod);
			pfisicaCadastro.setPessoa(pessoaFK);
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			pfdao.salvar(pfisicaCadastro);
			
			enderecoCadastro = new Endereco();
			pessoaCadastro = new Pessoa();
			pfisicaCadastro = new PessoaFisica();
			
			FacesUtil.addMsgInfo("Pessoa Fisica cadastrada com sucesso");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a pessoa física " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			listaPFisicas = pfdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar as pessoas" + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		
		try{
			if (codigo != null){
				PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
				pfisicaCadastro = pfdao.buscarPorCodigo(codigo);
				
				PessoaDAO pdao = new PessoaDAO();
				pessoaCadastro = pdao.buscarPorCodigo(pfisicaCadastro.getPessoa().getId());
				
				EnderecoDAO edao = new EnderecoDAO();
				enderecoCadastro = edao.buscarPorCodigo(pessoaCadastro.getEndereco().getId());
			}else{
				pfisicaCadastro = new PessoaFisica();
				pessoaCadastro = new Pessoa();
				enderecoCadastro = new Endereco();
			}
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados da pessoa" + ex.getMessage());
		}
		
	}
	
	public void editar(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			pfdao.editar(pfisicaCadastro);
			
			PessoaDAO pdao = new PessoaDAO();
			pdao.editar(pessoaCadastro);
			
			EnderecoDAO edao = new EnderecoDAO();
			edao.editar(enderecoCadastro);
			
			pfisicaCadastro = new PessoaFisica();
			pessoaCadastro = new Pessoa();
			enderecoCadastro = new Endereco();
			
			
			FacesUtil.addMsgInfo("Pessoa Fisica editar com sucesso");
		}catch (RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a pessoa" + ex.getMessage());
		}
	}
	
	public void excluir(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			pfdao.excluir(pfisicaCadastro);
			
			PessoaDAO pdao = new PessoaDAO();
			pdao.excluir(pessoaCadastro);
			
			EnderecoDAO edao = new EnderecoDAO();
			edao.excluir(enderecoCadastro);
			
			codigo = null;

			FacesUtil.addMsgInfo("Pessoa Fisica excluída com sucesso");
		}catch (RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a pessoa" + ex.getMessage());
		}
	}
	
}

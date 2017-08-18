package br.com.saudefinanceira.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.saudefinanceira.dao.EnderecoDAO;
import br.com.saudefinanceira.dao.PessoaDAO;
import br.com.saudefinanceira.domain.Endereco;
import br.com.saudefinanceira.domain.Pessoa;
import br.com.saudefinanceira.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PessoaBean {

	private Pessoa pessoaCadastro;
	private List<Pessoa> listaPessoas;
	private List<Pessoa> listaPessoasFiltradas;
	private List<Endereco> listaEnderecos;

	public Pessoa getPessoaCadastro() {
		return pessoaCadastro;
	}

	public void setPessoaCadastro(Pessoa pessoaCadastro) {
		this.pessoaCadastro = pessoaCadastro;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public List<Pessoa> getListaPessoasFiltradas() {
		return listaPessoasFiltradas;
	}

	public void setListaPessoasFiltradas(List<Pessoa> listaPessoasFiltradas) {
		this.listaPessoasFiltradas = listaPessoasFiltradas;
	}

	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public void novo() {
		pessoaCadastro = new Pessoa();
	}

	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.salvar(pessoaCadastro);

			pessoaCadastro = new Pessoa();

			FacesUtil.addMsgInfo("Pessoa cadastrada com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a pessoa" + ex.getMessage());
		}
	}

	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			listaPessoas = pessoaDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar as pessoas" + ex.getMessage());
		}
	}

	public void carregarDados() {
		try {
			String valor = 	FacesUtil.getParam("pessId");
			if (valor != null){
				Long codigo = Long.parseLong(valor);
				
				PessoaDAO pdao = new PessoaDAO();
				pessoaCadastro = pdao.buscarPorCodigo(codigo);
			}else{
				pessoaCadastro = new Pessoa();
			}
			
			EnderecoDAO edao = new EnderecoDAO();
			listaEnderecos = edao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao carregar os dados da pessoa" + ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoaCadastro);
			
			FacesUtil.addMsgInfo("Pessoa excluida com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao remover a pessoa" + ex.getMessage());
		}
	}
	
	public void editar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.editar(pessoaCadastro);

			FacesUtil.addMsgInfo("Pessoa editada com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a pessoa" + ex.getMessage());
		}
	}
}

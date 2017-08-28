package br.com.saudefinanceira.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.saudefinanceira.dao.DespesaDAO;
import br.com.saudefinanceira.domain.Despesa;
import br.com.saudefinanceira.util.FacesUtil;

@ManagedBean
@ViewScoped
public class DespesaBean {

	private Despesa despesaCadastro;
	private List<Despesa> listaDespesas;
	private List<Despesa> listaDespesasFiltradas;

	private String acao;
	private Long codigo;

	public Despesa getDespesaCadastro() {
		if (despesaCadastro == null){
			despesaCadastro = new Despesa();
		}
		return despesaCadastro;
	}

	public void setDespesaCadastro(Despesa despesaCadastro) {
		this.despesaCadastro = despesaCadastro;
	}

	public List<Despesa> getListaDespesas() {
		return listaDespesas;
	}

	public void setListaDespesas(List<Despesa> listaDespesas) {
		this.listaDespesas = listaDespesas;
	}

	public List<Despesa> getListaDespesasFiltradas() {
		return listaDespesasFiltradas;
	}

	public void setListaDespesasFiltradas(List<Despesa> listaDespesasFiltradas) {
		this.listaDespesasFiltradas = listaDespesasFiltradas;
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

	public void novo() {
		despesaCadastro = new Despesa();
	}

	public void salvar() {
		try {
			DespesaDAO ddao = new DespesaDAO();
			ddao.salvar(despesaCadastro);

			FacesUtil.addMsgInfo("Pagamento cadastrado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o pagamento " + ex.getMessage());
		}
	}

	public void listar() {
		try {
			DespesaDAO ddao = new DespesaDAO();
			listaDespesas = ddao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao listar os pagamentos " + ex.getMessage());
		}
	}

	public void carregarDados() {
		try {
			if (codigo != null) {
				DespesaDAO ddao = new DespesaDAO();
				despesaCadastro = ddao.buscarPorCodigo(codigo);
			} else {
				despesaCadastro = new Despesa();
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar o pagamento " + ex.getMessage());
		}
	}
	
	public void editar(){
		try {
			DespesaDAO ddao = new DespesaDAO();
			ddao.editar(despesaCadastro);

			FacesUtil.addMsgInfo("Pagamento editado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o pagamento " + ex.getMessage());
		}
	}
	
	public void excluir(){
		try {
			DespesaDAO ddao = new DespesaDAO();
			ddao.excluir(despesaCadastro);

			FacesUtil.addMsgInfo("Pagamento excluído com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o pagamento " + ex.getMessage());
		}
	}
}

package br.com.saudefinanceira.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.saudefinanceira.dao.DespesaDAO;
import br.com.saudefinanceira.dao.ItemDAO;
import br.com.saudefinanceira.dao.LancamentoDAO;
import br.com.saudefinanceira.dao.PessoaDAO;
import br.com.saudefinanceira.dao.ReceitaDAO;
import br.com.saudefinanceira.domain.Despesa;
import br.com.saudefinanceira.domain.Item;
import br.com.saudefinanceira.domain.Lancamento;
import br.com.saudefinanceira.domain.Pessoa;
import br.com.saudefinanceira.domain.Receita;
import br.com.saudefinanceira.util.FacesUtil;

@ManagedBean
@ViewScoped
public class LancamentoBean {

	private Lancamento lancamentoCadastro;

	private List<Lancamento> listaLancamentos;
	private List<Lancamento> listaLancamentosFiltrados;
	private List<Pessoa> listaPessoas;
	private List<Item> listaItens;
	private List<Item> listaItensFiltrados;
	private List<Despesa> listaDespesas;
	private List<Receita> listaReceitas;

	private String acao;
	private Long codigo;

	public Lancamento getLancamentoCadastro() {
		if (lancamentoCadastro == null) {
			lancamentoCadastro = new Lancamento();
			lancamentoCadastro.setTotal(new BigDecimal("0.00"));
		}
		return lancamentoCadastro;
	}

	public void setLancamentoCadastro(Lancamento lancamentoCadastro) {
		this.lancamentoCadastro = lancamentoCadastro;
	}

	public List<Lancamento> getListaLancamentos() {
		return listaLancamentos;
	}

	public void setListaLancamentos(List<Lancamento> listaLancamentos) {
		this.listaLancamentos = listaLancamentos;
	}

	public List<Lancamento> getListaLancamentosFiltrados() {
		return listaLancamentosFiltrados;
	}

	public void setListaLancamentosFiltrados(List<Lancamento> listaLancamentosFiltrados) {
		this.listaLancamentosFiltrados = listaLancamentosFiltrados;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public List<Item> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}

	public List<Item> getListaItensFiltrados() {
		return listaItensFiltrados;
	}

	public void setListaItensFiltrados(List<Item> listaItensFiltrados) {
		this.listaItensFiltrados = listaItensFiltrados;
	}

	public List<Despesa> getListaDespesas() {
		if (listaDespesas == null) {
			listaDespesas = new ArrayList<>();
		}
		return listaDespesas;
	}

	public void setListaDespesas(List<Despesa> listaDespesas) {
		this.listaDespesas = listaDespesas;
	}

	public List<Receita> getListaReceitas() {
		if (listaReceitas == null) {
			listaReceitas = new ArrayList<>();
		}
		return listaReceitas;
	}

	public void setListaReceitas(List<Receita> listaReceitas) {
		this.listaReceitas = listaReceitas;
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
		lancamentoCadastro = new Lancamento();
	}

	public void listarDespesas() {
		try {
			DespesaDAO ddao = new DespesaDAO();
			listaDespesas = ddao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar as despesas " + ex.getMessage());
		}
	}

	public void listarReceitas() {
		try {
			ReceitaDAO rdao = new ReceitaDAO();
			listaReceitas = rdao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar as Receitas " + ex.getMessage());
		}
	}

	public void listar() {
		try {
			LancamentoDAO ldao = new LancamentoDAO();
			listaLancamentos = ldao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os lançamentos " + ex.getMessage());
		}
	}

	public void carregarDados() {
		try {
			if (codigo != null) {
				LancamentoDAO ldao = new LancamentoDAO();
				lancamentoCadastro = ldao.buscarPorCodigo(codigo);
			} else {
				lancamentoCadastro = new Lancamento();
			}
			PessoaDAO pdao = new PessoaDAO();
			listaPessoas = pdao.listar();

			ItemDAO idao = new ItemDAO();
			listaItens = idao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao carregar os dados dos lançamentos" + ex.getMessage());
		}
	}

	public void adicionarDespesa(Item item) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaDespesas.size() && posicaoEncontrada < 0; pos++) {
			Despesa despesaTemp = listaDespesas.get(pos);

			if (despesaTemp.getItem().equals(item)) {
				posicaoEncontrada = pos;
			}

		}
		Despesa despesa = new Despesa();
		despesa.setItem(item);

		if (posicaoEncontrada < 0) {
			despesa.setQuantidade(1);
			despesa.setValorUnitario(item.getValor());

			listaDespesas.add(despesa);
		} else {
			Despesa despesaTemp = listaDespesas.get(posicaoEncontrada);
			despesa.setQuantidade(despesaTemp.getQuantidade() + 1);
			despesa.setValorUnitario(item.getValor().multiply(new BigDecimal(despesa.getQuantidade())));
			listaDespesas.set(posicaoEncontrada, despesa);
		}
	}
	
	public void removerDespesa(Despesa despesa) {
			
			int posicaoEncontrada = -1;

			for (int pos = 0; pos < listaDespesas.size() && posicaoEncontrada < 0; pos++) {
				Despesa despesaTemp = listaDespesas.get(pos);

				if (despesaTemp.getItem().equals(despesa.getItem())) {
					posicaoEncontrada = pos;
				}
			}
			
			if (posicaoEncontrada > -1) {
				listaDespesas.remove(posicaoEncontrada);				
			}
			
	}

	public void salvar() {
		try {
			LancamentoDAO ldao = new LancamentoDAO();
			ldao.salvar(lancamentoCadastro);

			FacesUtil.addMsgInfo("Lançamento cadastrado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o lançamento" + ex.getMessage());
		}
	}

	public void excluir() {
		try {
			LancamentoDAO ldao = new LancamentoDAO();
			ldao.excluir(lancamentoCadastro);

			FacesUtil.addMsgInfo("Lançamento excluído com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o lançamento" + ex.getMessage());
		}
	}

	public void editar() {
		try {
			LancamentoDAO ldao = new LancamentoDAO();
			ldao.editar(lancamentoCadastro);

			FacesUtil.addMsgInfo("Lançamento editado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o lançamento" + ex.getMessage());
		}
	}
}

package br.com.saudefinanceira.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.saudefinanceira.dao.ItemDAO;
import br.com.saudefinanceira.domain.Item;
import br.com.saudefinanceira.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ItemBean {

	private Item itemCadastro;
	private List<Item> listaItens;
	private List<Item> listaItensFiltrados;
	
	private String acao;
	private Long codigo;
	public Item getItemCadastro() {
		return itemCadastro;
	}
	public void setItemCadastro(Item itemCadastro) {
		this.itemCadastro = itemCadastro;
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
		itemCadastro = new Item();
	}
	
	public void salvar(){
		try{
			ItemDAO idao = new ItemDAO();
			idao.salvar(itemCadastro);
			
			FacesUtil.addMsgInfo("Item cadastrado com sucesso");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o item " + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			ItemDAO idao = new ItemDAO();
			listaItens = idao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os itens " + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if (codigo != null){
				ItemDAO idao = new ItemDAO();
				itemCadastro = idao.buscarPorCodigo(codigo);
			}else{
				itemCadastro = new Item();
			}
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os itens " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			ItemDAO idao = new ItemDAO();
			idao.editar(itemCadastro);
			
			FacesUtil.addMsgInfo("Item editado com sucesso");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o item " + ex.getMessage());
		}
	}
	
	public void excluir(){
		try{
			ItemDAO idao = new ItemDAO();
			idao.excluir(itemCadastro);
			
			FacesUtil.addMsgInfo("Item excluído com sucesso");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o item " + ex.getMessage());
		}
	}
}

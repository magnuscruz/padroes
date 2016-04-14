package br.unifor.padroes.web.mbean;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.unifor.padroes.entidades.Produto;
import br.unifor.padroes.servicelocator.ServiceLocator;
import br.unifor.padroes.servicelocator.impl.FactoryServiceLocator;
import br.unifor.padroes.services.ProdutoService;
import br.unifor.padroes.web.mbean.util.ProdutoPaginatorModel;
import br.unifor.padroes.web.util.FacesUtil;

public class ProdutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8416197507824094611L;
	private static ServiceLocator instance = FactoryServiceLocator
			.getInstance("compras");
	private String nome;
	private Double valor;
	private ProdutoService service;
	private List<Produto> produtoLista;
	private Produto produto;
	private ProdutoPaginatorModel dataModel;

	public ProdutoBean() {
		service = (ProdutoService) instance.getService(ProdutoService.class);
		produto = new Produto();
	}

	@PostConstruct
	public void init() {
		dataModel = new ProdutoPaginatorModel(null);
	}

	public String pesquisar() {
		try {
			Produto filtro = new Produto();
			filtro.setNome(nome);

			setDataModel(new ProdutoPaginatorModel(filtro));
			FacesUtil.addInfoMessage("Quantidade de Produtos encontrados foi "
					+ getDataModel().getRowCount() + "!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro ao consultar Produtos!");
			//throw e;
		}
		return "";

	}

	public String selecionar() {
		if (produto != null && produto.getCodigo() != null) {
			setNome(produto.getNome());
			setValor(produto.getValor());
		}
		return "";
	}

	public String inserir() {
		try {
			produto = new Produto();
			produto.setNome(nome);
			produto.setValor(valor);

			List<Produto> produtos = new ArrayList<Produto>();
			produtos.add(produto);
			service.inserir(produto);
			setDataModel(new ProdutoPaginatorModel(null));
			FacesUtil.addInfoMessage("Produto inserido com sucesso!");
			setNome(null);
			setValor(null);
			setProduto(new Produto());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(SEVERITY_ERROR, "Produto NÃO inserido!",
							null));
		}
		return "";
	}

	public String alterar() {
		try {
			produto.setNome(nome);
			produto.setValor(valor);
			service.alterar(produto);
			setDataModel(new ProdutoPaginatorModel(null));
			FacesUtil.addInfoMessage("Produto inserido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(SEVERITY_ERROR, "Produto NÃO alterado!",
							null));
		}
		return "";
	}

	public String remover() {
		try {
			service.remover(produto.getCodigo());
			setDataModel(new ProdutoPaginatorModel(null));
			FacesUtil.addInfoMessage("Produto removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(SEVERITY_ERROR, "Produto NÃO removido!",
							null));
		}
		return "";
	}

	public void onRowSelect(SelectEvent event) {
		produto = (Produto) event.getObject();
		if (produto != null && produto.getCodigo() != null) {
			setNome(produto.getNome());
			setValor(produto.getValor());
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		produto = null;
		setNome(null);
		setValor(null);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<Produto> getProdutoLista() {
		return produtoLista;
	}

	public void setProdutoLista(List<Produto> produtoLista) {
		this.produtoLista = produtoLista;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoPaginatorModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(ProdutoPaginatorModel dataModel) {
		this.dataModel = dataModel;
	}
}

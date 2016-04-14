package br.unifor.padroes.web.mbean.util;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.unifor.padroes.entidades.Produto;
import br.unifor.padroes.servicelocator.ServiceLocator;
import br.unifor.padroes.servicelocator.impl.FactoryServiceLocator;
import br.unifor.padroes.services.ProdutoService;
import br.unifor.padroes.web.mbean.SistemaBean;

public class ProdutoPaginatorModel extends LazyDataModel<Produto> implements SelectableDataModel<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1375723749369464693L;

	private static ServiceLocator instance = FactoryServiceLocator
			.getInstance("compras");

	private ProdutoService service;

	private Produto filtro;

	/**
	 *
	 */
	public ProdutoPaginatorModel(Produto filtro) {
		super();
		this.filtro = filtro;
		service = (ProdutoService) instance.getService(ProdutoService.class);
		load(0, new SistemaBean().getPageSize(), null, null, null);
	}

	@Override
	public Object getRowKey(Produto object) {
		return object.getCodigo();
	}

	@Override
	public Produto getRowData(String rowKey) {
		return service.buscarPorId(Integer.valueOf(rowKey));
	}

	@Override
	public List<Produto> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		if (filtro == null) {
			setRowCount(service.contarTodos());
		} else {
			setRowCount(service.contarComFiltro(filtro));
		}
		if (filtro == null) {
			return service.buscarTodos(first, pageSize);
		} else {
			return service.buscarPorFiltro(filtro, first, pageSize);
		}
	}

	@Override
	public List<Produto> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		if (filtro == null) {
			setRowCount(service.contarTodos());
		} else {
			setRowCount(service.contarComFiltro(filtro));
		}
		if (filtro == null) {
			return service.buscarTodos(first, pageSize);
		} else {
			return service.buscarPorFiltro(filtro, first, pageSize);
		}
		
	}
}

package br.unifor.infra.core.services.impl;

import java.io.Serializable;
import java.util.List;

import br.unifor.infra.core.dao.SistemaDao;
import br.unifor.infra.core.services.SistemaService;

public abstract class AbstractServiceImpl<E extends Serializable, F extends Serializable, ID>
		implements SistemaService<E, F, ID> {
	public void inserir(E entidade) {
		getDao().inserir(entidade);
	}

	public List<E> buscarTodos(int firstResult, int maxResult) {
		return getDao().buscarTodos(getEntidadeClass(), firstResult, maxResult);
	}

	public E alterar(E entidade) {
		return getDao().alterar(entidade);
	}

	public E buscarPorId(ID id) {
		return getDao().buscarPorId(getEntidadeClass(), id);
	}

	public void remover(ID id) {
		getDao().remover(getEntidadeClass(), id);
	}

	public List<E> buscarTodos() {
		return getDao().buscarTodos(getEntidadeClass());
	}

	public Integer contarTodos() {
		return getDao().contarTodos(getEntidadeClass());
	}

	public List<E> buscarPorFiltro(F filtro) {
		return getDao().buscarPorFiltro(getEntidadeClass(), filtro);
	}

	public Integer contarComFiltro(F filtro) {
		return getDao().contarComFiltro(getEntidadeClass(), filtro);
	}

	public List<E> buscarPorFiltro(F filtro, int firstResult, int maxResult) {
		return getDao().buscarPorFiltro(getEntidadeClass(), filtro,
				firstResult, maxResult);
	}

	protected abstract SistemaDao<E, F, ID> getDao();

	protected abstract Class<E> getEntidadeClass();

}

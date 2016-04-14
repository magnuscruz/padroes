package br.unifor.padroes.services.impl;

import java.io.Serializable;
import java.util.List;

import br.unifor.padroes.dao.SistemaDao;
import br.unifor.padroes.services.SistemaService;

public abstract class AbstractServiceImpl<E extends Serializable, F extends Serializable, ID>
		implements SistemaService<E, F, ID> {
	@Override
	public void inserir(E entidade) {
		getDao().inserir(entidade);
	}

	@Override
	public List<E> buscarTodos(int firstResult, int maxResult) {
		return getDao().buscarTodos(getEntidadeClass(), firstResult, maxResult);
	}

	@Override
	public E alterar(E entidade) {
		return getDao().alterar(entidade);
	}

	@Override
	public E buscarPorId(ID id) {
		return getDao().buscarPorId(getEntidadeClass(), id);
	}

	@Override
	public void remover(ID id) {
		getDao().remover(getEntidadeClass(), id);
	}

	@Override
	public List<E> buscarTodos() {
		return getDao().buscarTodos(getEntidadeClass());
	}

	@Override
	public Integer contarTodos() {
		return getDao().contarTodos(getEntidadeClass());
	}

	@Override
	public List<E> buscarPorFiltro(F filtro) {
		return getDao().buscarPorFiltro(getEntidadeClass(), filtro);
	}

	@Override
	public Integer contarComFiltro(F filtro) {
		return getDao().contarComFiltro(getEntidadeClass(), filtro);
	}

	@Override
	public List<E> buscarPorFiltro(F filtro, int firstResult, int maxResult) {
		return getDao().buscarPorFiltro(getEntidadeClass(), filtro,
				firstResult, maxResult);
	}

	protected abstract SistemaDao<E, F, ID> getDao();

	protected abstract Class<E> getEntidadeClass();

}

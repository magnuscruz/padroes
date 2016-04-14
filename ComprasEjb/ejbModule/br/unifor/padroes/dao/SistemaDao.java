package br.unifor.padroes.dao;

import java.io.Serializable;
import java.util.List;

public interface SistemaDao<E extends Serializable, F extends Serializable, ID> {

	void inserir(E entidade);

	E alterar(E entidade);

	E buscarPorId(Class<E> clazz, ID id);

	void remover(Class<E> clazz, ID id);

	List<E> buscarTodos(Class<E> clazz);

	List<E> buscarTodos(Class<E> clazz, int firstResult, int maxResult);

	List<E> buscarPorFiltro(Class<E> clazz, F filtro);

	List<E> buscarPorFiltro(Class<E> clazz, F filtro, int firstResult,
			int maxResult);

	Integer contarTodos(Class<E> clazz);

	Integer contarComFiltro(Class<E> clazz, F filtro);
}

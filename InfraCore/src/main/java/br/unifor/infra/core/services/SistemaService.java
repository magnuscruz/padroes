package br.unifor.infra.core.services;

import java.io.Serializable;
import java.util.List;

public interface SistemaService<E extends Serializable, F extends Serializable, ID> {
	void inserir(E entidade);

	E alterar(E entidade);

	E buscarPorId(ID id);
	
	void remover(ID id);

	List<E> buscarTodos();

	Integer contarTodos();

	List<E> buscarTodos(int firstResult, int maxResult);

	List<E> buscarPorFiltro(F filtro);

	Integer contarComFiltro(F filtro);

	List<E> buscarPorFiltro(F filtro, int firstResult, int maxResult);
}

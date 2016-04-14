package br.unifor.padroes.jpa;

import java.util.List;

public interface CRUDDao<T, ID> {
	public void save(T entity);

	public void delete(T entity);

	public void update(T entity);

	public List<T> listAll();

	public T findById(ID id);
}
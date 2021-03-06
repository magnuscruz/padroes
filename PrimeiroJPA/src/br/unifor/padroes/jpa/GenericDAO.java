package br.unifor.padroes.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public abstract class GenericDAO<T, ID> implements CRUDDao<T, ID> {

	public GenericDAO() {
	}

	@Override
	public void save(T entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
		getEntityManager().getTransaction().commit();
	}

	@Override
	public void delete(T entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(entity);
		getEntityManager().getTransaction().commit();
	}

	@Override
	public void update(T entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
	}

	@Override
	public List<T> listAll() {
		TypedQuery<T> query = getEntityManager()
				.createQuery(
						"select o from "
								+ getPersistenceClass().getSimpleName() + " o",
						getPersistenceClass());
		List<T> list = query.getResultList();
		return list;
	}

	@Override
	public List<T> listAll(Integer firstResult, Integer maxResult) {
		TypedQuery<T> query = getEntityManager()
				.createQuery(
						"select o from "
								+ getPersistenceClass().getSimpleName() + " o",
						getPersistenceClass());
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<T> list = query.getResultList();
		return list;
	}

	@Override
	public Long countAll() {
		TypedQuery<Long> query = getEntityManager().createQuery(
				"select count(o) from " + getPersistenceClass().getSimpleName()
						+ " o", Long.class);
		Long count = query.getSingleResult();
		return count;
	}

	protected abstract Class<T> getPersistenceClass();

	@Override
	public T findById(ID id) {
		T aluno = getEntityManager().find(getPersistenceClass(), id);
		return aluno;
	}

	public abstract EntityManager getEntityManager();
}
package br.unifor.infra.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.unifor.infra.core.dao.SistemaDao;

public abstract class AbstractDao<E extends Serializable, F extends Serializable, ID>
		implements SistemaDao<E, F, ID> {

	@PersistenceContext
	private EntityManager entityManager;

	public E buscarPorId(Class<E> clazz, ID id) {
		E entidade = getEntityManager().find(clazz, id);
		return entidade;
	}

	public List<E> buscarTodos(Class<E> clazz) {
		Query query = getEntityManager().createQuery(
				"select e from " + clazz.getSimpleName() + " e", clazz);
		@SuppressWarnings("unchecked")
		List<E> list = query.getResultList();
		return list;
	}

	public List<E> buscarTodos(Class<E> clazz, int firstResult, int maxResult) {

		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<E> root = criteriaQuery.from(clazz);
		criteriaQuery.select(root);
		TypedQuery<E> query = extractQueryPaginator(firstResult, maxResult,
				criteriaQuery);

		List<E> list = query.getResultList();
		return list;
	}

	public List<E> buscarPorFiltro(Class<E> clazz, F filtro) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<E> root = criteriaQuery.from(clazz);
		CriteriaQuery<E> select = criteriaQuery.select(root);
		extrairFiltros(filtro, criteriaBuilder, select, root);

		List<E> list = getEntityManager().createQuery(criteriaQuery).getResultList();
		return list;
	}

	public List<E> buscarPorFiltro(Class<E> clazz, F filtro, int firstResult,
			int maxResult) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<E> root = criteriaQuery.from(clazz);
		CriteriaQuery<E> select = criteriaQuery.select(root);
		extrairFiltros(filtro, criteriaBuilder, select, root);
		
		TypedQuery<E> query = extractQueryPaginator(firstResult, maxResult,
				select);

		List<E> list = query.getResultList();
		return list;
	}

	private TypedQuery<E> extractQueryPaginator(int firstResult, int maxResult,
			CriteriaQuery<E> criteriaQuery) {
		TypedQuery<E> query = getEntityManager().createQuery(criteriaQuery);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query;
	}

	public Integer contarTodos(Class<E> clazz) {
		Query query = getEntityManager().createQuery(
				"select count(e) from " + clazz.getSimpleName() + " e", clazz);
		Integer count = Integer.valueOf(query.getSingleResult().toString());
		return count;
	}

	public Integer contarComFiltro(Class<E> clazz, F filtro) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder
				.createQuery(clazz);
		CriteriaQuery<Long> criteriaQuery1 = criteriaBuilder
				.createQuery(Long.class);
		Root<E> root = criteriaQuery1.from(clazz);

		extrairFiltros(filtro, criteriaBuilder, criteriaQuery1, root);
		
		criteriaQuery1.select(criteriaBuilder.count(root));

		Integer count = Integer.valueOf(getEntityManager()
				.createQuery(criteriaQuery1).getSingleResult().toString());
		return count;
	}

	protected abstract void extrairFiltros(F filtro,
			CriteriaBuilder criteriaBuilder, CriteriaQuery criteriaQuery,
			Root<E> root);

	public void inserir(E entidade) {
		getEntityManager().persist(entidade);
	}

	public E alterar(E entidade) {
		E entidadeAlterada = getEntityManager().merge(entidade);
		return entidadeAlterada;
	}

	public void remover(Class<E> clazz, ID id) {
		getEntityManager().remove(buscarPorId(clazz, id));
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}
}

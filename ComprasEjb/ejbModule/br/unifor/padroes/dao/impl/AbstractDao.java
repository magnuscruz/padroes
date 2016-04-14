package br.unifor.padroes.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.unifor.padroes.dao.SistemaDao;
import br.unifor.padroes.util.Geral;

public abstract class AbstractDao<E extends Serializable, F extends Serializable, ID>
		implements SistemaDao<E, F, ID> {

	@Inject
	@Geral
	protected EntityManager entityManager;

	@Override
	public E buscarPorId(Class<E> clazz, ID id) {
		entityManager.getTransaction().begin();
		E entidade = entityManager.find(clazz, id);
		entityManager.getTransaction().commit();
		return entidade;
	}

	@Override
	public List<E> buscarTodos(Class<E> clazz) {
		Query query = entityManager.createQuery(
				"select e from " + clazz.getSimpleName() + " e", clazz);
		@SuppressWarnings("unchecked")
		List<E> list = query.getResultList();
		return list;
	}

	@Override
	public List<E> buscarTodos(Class<E> clazz, int firstResult, int maxResult) {
		entityManager.getTransaction().begin();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<E> root = criteriaQuery.from(clazz);
		criteriaQuery.select(root);
		TypedQuery<E> query = extractQueryPaginator(firstResult, maxResult,
				criteriaQuery);

		List<E> list = query.getResultList();
		entityManager.getTransaction().commit();
		return list;
	}

	public List<E> buscarPorFiltro(Class<E> clazz, F filtro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<E> root = criteriaQuery.from(clazz);
		CriteriaQuery<E> select = criteriaQuery.select(root);
		extrairFiltros(filtro, criteriaBuilder, select, root);

		List<E> list = entityManager.createQuery(criteriaQuery).getResultList();
		return list;
	}

	public List<E> buscarPorFiltro(Class<E> clazz, F filtro, int firstResult,
			int maxResult) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
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
		TypedQuery<E> query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query;
	}

	@Override
	public Integer contarTodos(Class<E> clazz) {
		Query query = entityManager.createQuery(
				"select count(e) from " + clazz.getSimpleName() + " e", clazz);
		Integer count = Integer.valueOf(query.getSingleResult().toString());
		return count;
	}

	@Override
	public Integer contarComFiltro(Class<E> clazz, F filtro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder
				.createQuery(clazz);
		CriteriaQuery<Long> criteriaQuery1 = criteriaBuilder
				.createQuery(Long.class);
		Root<E> root = criteriaQuery1.from(clazz);

		extrairFiltros(filtro, criteriaBuilder, criteriaQuery1, root);
		
		criteriaQuery1.select(criteriaBuilder.count(root));

		Integer count = Integer.valueOf(entityManager
				.createQuery(criteriaQuery1).getSingleResult().toString());
		return count;
	}

	protected abstract void extrairFiltros(F filtro,
			CriteriaBuilder criteriaBuilder, CriteriaQuery criteriaQuery,
			Root<E> root);

	@Override
	public void inserir(E entidade) {
		entityManager.getTransaction().begin();
		entityManager.persist(entidade);
		entityManager.getTransaction().commit();
	}

	@Override
	public E alterar(E entidade) {
		entityManager.getTransaction().begin();
		E entidadeAlterada = entityManager.merge(entidade);
		entityManager.getTransaction().commit();
		return entidadeAlterada;
	}

	@Override
	public void remover(Class<E> clazz, ID id) {
		entityManager.remove(buscarPorId(clazz, id));
	}
}

package br.unifor.padroes.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Geral
public class TarefaDAOImpl extends GenericDAO<Tarefa, Long> implements TarefaDAO {

	@Inject
	@Geral
	private EntityManager entityManager;
	
	public TarefaDAOImpl() {
		super();
	}

	@Override
	protected Class<Tarefa> getPersistenceClass() {
		return Tarefa.class;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}

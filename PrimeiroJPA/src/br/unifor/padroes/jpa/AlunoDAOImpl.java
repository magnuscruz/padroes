package br.unifor.padroes.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Geral
public class AlunoDAOImpl extends GenericDAO<Aluno, Long> implements AlunoDAO {

	@Inject
	@Geral
	private EntityManager entityManager;
	
	public AlunoDAOImpl() {
		super();
	}

	@Override
	protected Class<Aluno> getPersistenceClass() {
		return Aluno.class;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}

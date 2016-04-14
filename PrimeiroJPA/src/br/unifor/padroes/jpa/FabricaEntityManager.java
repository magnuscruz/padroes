package br.unifor.padroes.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaEntityManager {

	@Produces
	@ApplicationScoped
	@Geral
	public EntityManagerFactory criaEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("PrimeiroJPA");
	}

	@Produces
	@ApplicationScoped
	@Geral
	public EntityManager criaEntityManager(@Geral EntityManagerFactory factory) {
		return factory.createEntityManager();
	}


	@Produces
	@ApplicationScoped
	@GeralHiber
	public EntityManagerFactory criaEntityManagerFactoryH() {
		return Persistence.createEntityManagerFactory("HibernateJPA");
	}

	@Produces
	@ApplicationScoped
	@GeralHiber
	public EntityManager criaEntityManagerH(@GeralHiber EntityManagerFactory factory) {
		return factory.createEntityManager();
	}
//	public void finaliza(@Disposes EntityManager manager) {
//		manager.close();
//	}
}
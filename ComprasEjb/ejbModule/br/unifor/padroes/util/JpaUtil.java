package br.unifor.padroes.util;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;




/**
 * @author MENINOS
 *
 */
@Stateless
public class JpaUtil {
	@PersistenceUnit(unitName="ComprasPU")
	private EntityManagerFactory entityManagerFactory;
	/**
	 * @param entityManagerFactory
	 * @return
	 */
	@Produces @Geral @RequestScoped
	public EntityManager criarEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
//	@Produces @Geral @ApplicationScoped
//	public EntityManagerFactory criarEntityManagerFactory() {
////		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ComprasPU");
//		return entityManagerFactory;
//	}
}

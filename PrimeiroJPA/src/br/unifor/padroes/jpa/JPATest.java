package br.unifor.padroes.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


public class JPATest extends TestCase {

	private EntityManager entityManager;

	@Before
	public void setUp() throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PrimeiroJPA");
		entityManager = factory.createEntityManager();
	}

	@Test
	public void testInserirAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome("MAGNUS ALENCAR DA CRUZ");
		entityManager.persist(aluno);
//		entityManager.flush();
	}

}

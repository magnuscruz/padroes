package br.unifor.padroes.test;

import java.util.List;

import javax.inject.Inject;

import junit.framework.TestCase;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.unifor.padroes.jpa.Aluno;
import br.unifor.padroes.jpa.AlunoDAOImpl;
import br.unifor.padroes.jpa.CRUDDao;
import br.unifor.padroes.jpa.FabricaEntityManager;
import br.unifor.padroes.jpa.Geral;

/**
 * @author professort7
 * @since 31/03/2016
 *
 */
@RunWith(CdiRunner.class)
// Runs the test with CDI-Unit
@AdditionalClasses({ AlunoDAOImpl.class, FabricaEntityManager.class})
public class AlunoDAOTest extends TestCase {

	/**
	 * Atributo com a instancia do DAO de Aluno
	 */
	@Geral
	@Inject
	private CRUDDao<Aluno, Long> dao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInserirAluno() {
		// Noava intancia de aluno
		Aluno aluno = new Aluno();
		aluno.setNome("MAGNUS ALENCAR DA CRUZ");
		dao.save(aluno);
		assertNotNull(aluno.getId());
	}

	/**
	 * Testa a fun��o de atualiza��o de aluno.
	 */
	@Test
	public void testUpdateAluno() {
		Aluno aluno = new Aluno();
		String nome = "ANTONIO DA SILVA VIANA";
		aluno.setNome(nome);
		dao.save(aluno);
		assertNotNull(aluno.getId());
		nome = "MARIA DAS GRACAS ROCHA";
		aluno.setNome(nome);
		dao.update(aluno);
		Aluno aluno2 = dao.findById(aluno.getId());
		assertEquals(nome, aluno2.getNome());
	}

	@Test
	public void testFindAllAluno() {
		List<Aluno> list = dao.listAll();
		int size = list.size();

		Aluno aluno = new Aluno();
		String nome = "ANTONIO DA SILVA VIANA";
		aluno.setNome(nome);
		dao.save(aluno);
		list = dao.listAll();
		assertEquals(list.size(), size + 1);

		dao.delete(aluno);
		list = dao.listAll();
		assertEquals(list.size(), size);
	}
}

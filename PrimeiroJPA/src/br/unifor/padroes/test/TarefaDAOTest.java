package br.unifor.padroes.test;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import junit.framework.TestCase;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.unifor.padroes.jpa.CRUDDao;
import br.unifor.padroes.jpa.FabricaEntityManager;
import br.unifor.padroes.jpa.Geral;
import br.unifor.padroes.jpa.Tarefa;
import br.unifor.padroes.jpa.TarefaDAOImpl;

/**
 * @author professort7
 * @since 31/03/2016
 *
 */
@RunWith(CdiRunner.class)
// Runs the test with CDI-Unit
@AdditionalClasses({ TarefaDAOImpl.class, FabricaEntityManager.class })
public class TarefaDAOTest extends TestCase {

	private static final String DESC3 = "TAREFA NUMERO 3";
	private static final String DESC2 = "TAREFA NUMERO 2";
	private static final String DESC1 = "TAREFA NUMERO 1";
	/**
	 * Atributo com a instancia do DAO de Tarefa
	 */
	@Geral
	@Inject
	private CRUDDao<Tarefa, Long> dao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInserirTarefa() {
		// Noava intancia de tarefa
		Tarefa tarefa = new Tarefa();
		tarefa.setDescricao(DESC1);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		dao.save(tarefa);
		assertNotNull(tarefa.getId());
	}

	/**
	 * Testa a fun��o de atualiza��o de tarefa.
	 */
	@Test
	public void testUpdateTarefa() {
		Tarefa tarefa = new Tarefa();
		String nome = DESC2;
		tarefa.setDescricao(nome);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		dao.save(tarefa);
		assertNotNull(tarefa.getId());
		nome = DESC3;
		tarefa.setDescricao(nome);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		dao.update(tarefa);
		Tarefa tarefa2 = dao.findById(tarefa.getId());
		assertEquals(nome, tarefa2.getDescricao());
	}

	@Test
	public void testFindAllTarefa() {
		List<Tarefa> list = dao.listAll();
		int size = list.size();

		Tarefa tarefa = new Tarefa();
		String nome = DESC2;
		tarefa.setDescricao(nome);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		dao.save(tarefa);
		list = dao.listAll();
		assertEquals(list.size(), size + 1);

		dao.delete(tarefa);
		list = dao.listAll();
		assertEquals(list.size(), size);
	}
}

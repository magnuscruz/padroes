import org.junit.Test;

import br.unifor.padroes.OutputBuilder;

public abstract class AbstractBuilderTest {

	public AbstractBuilderTest() {
		super();
	}

	protected abstract OutputBuilder createBuilder(String nome);

	@Test
	public void testAddAboveRoot() {
		OutputBuilder builder = createBuilder("market");
		System.out.println("ADICIONANDO-"+builder);
	}
	

//	@Test
//	public void testRemoveAboveRoot() {
//		OutputBuilder builder = createBuilder("market");
//		System.out.println("REMOVENDO-"+builder);
//	}

}
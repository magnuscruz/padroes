import org.junit.Assert;
import org.junit.Test;

import br.unifor.padroes.OutputBuilder;
import br.unifor.padroes.XMLBuilder;

public class XMLBuilderTest extends AbstractBuilderTest {
	private OutputBuilder xmlBuilder;

	@Override
	protected OutputBuilder createBuilder(String nome) {
		if (xmlBuilder == null || !xmlBuilder.getNome().equals(nome)) {
			xmlBuilder = new XMLBuilder(nome);
		}
		return xmlBuilder;
	}

	@Test
	public void createValidXML() {
		String validXML = "<market><orders><order></order><order></order><order></order></orders><customer></customer></market>";
		xmlBuilder = createBuilder("market");
		xmlBuilder.addBelow("orders");
		xmlBuilder.addBelow("customer");
		xmlBuilder.addBelow("order", "orders");
		xmlBuilder.addBelow("order", "orders");
		xmlBuilder.addBelow("order", "orders");
		System.out.println(xmlBuilder);
		Assert.assertEquals(validXML, xmlBuilder.getDocument().toString());
	}

	@Test
	public void createInvalidXML() {
		String invalidXML = "<market><orders><order></order><order></order><order></order></orders><customer></customer>";
		xmlBuilder = createBuilder("market");
		xmlBuilder.addBelow("orders");
		xmlBuilder.addBelow("customer");
		xmlBuilder.addBelow("order", "orders");
		xmlBuilder.addBelow("order", "orders");
		xmlBuilder.addBelow("order", "orders");
		System.out.println(xmlBuilder);
		Assert.assertNotEquals(invalidXML, xmlBuilder.getDocument().toString());
	}
}

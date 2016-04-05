import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.junit.Test;

import br.unifor.padroes.OutputBuilder;
import br.unifor.padroes.TagBuilder;
import br.unifor.padroes.TagNode;

public class TagBuilderTest extends AbstractBuilderTest {
	private static final String MAE_TE_AMO = "MÃE TE AMO!!!";
	private static final String REQUIREMENTS = "requirements";
	private static final String REQUIREMENT = "requirement";
	private static final String FLAVOR = "flavor";
	private static final String FLAVORS = "flavors";
	private OutputBuilder domBuilder;

	@Override
	protected OutputBuilder createBuilder(String nome) {
		if (domBuilder == null || !domBuilder.getNome().equals(nome)) {
			domBuilder = new TagBuilder(nome);
		}
		return domBuilder;
	}

	@Test
	public void testBuildOneNode() {
		String expectedXml = TagNode.ABRE + FLAVORS + TagNode.FECHA2;
		TagBuilder tagBuilder = new TagBuilder(FLAVORS);
		String actualXml = tagBuilder.toXml();
		System.out.println(actualXml);
		assertEquals(expectedXml, actualXml);
	}

	@Test
	public void testBuildOneChild() {
		String expectedXml = TagNode.ABRE + FLAVORS + TagNode.FECHA
				+ TagNode.ABRE + FLAVOR + TagNode.FECHA2 + TagNode.ABRE2
				+ FLAVORS + TagNode.FECHA;
		TagBuilder tagBuilder = new TagBuilder(FLAVORS);
		tagBuilder.addChild(FLAVOR);
		String actualXml = tagBuilder.toXml();
		System.out.println(actualXml);
		assertEquals(expectedXml, actualXml);
	}

	@Test
	public void testBuildOtherChild() {
		String expectedXml = TagNode.ABRE + FLAVORS + TagNode.FECHA
				+ TagNode.ABRE + FLAVOR + TagNode.FECHA2 + TagNode.ABRE
				+ FLAVOR + TagNode.FECHA2 + TagNode.ABRE2 + FLAVORS
				+ TagNode.FECHA;
		TagBuilder tagBuilder = new TagBuilder(FLAVORS);
		tagBuilder.addChild(FLAVOR);
		tagBuilder.addSibling(FLAVOR);
		String actualXml = tagBuilder.toXml();
		System.out.println(actualXml);
		assertEquals(expectedXml, actualXml);
	}

	@Test
	public void testBuildChildrenOfChildren() {
		String expectedXml = TagNode.ABRE + FLAVORS + TagNode.FECHA
				+ TagNode.ABRE + FLAVOR + TagNode.FECHA
				+ TagNode.ABRE + REQUIREMENTS + TagNode.FECHA
				+ TagNode.ABRE + REQUIREMENT + TagNode.FECHA2
				+ TagNode.ABRE2 + REQUIREMENTS + TagNode.FECHA
				+ TagNode.ABRE2 + FLAVOR + TagNode.FECHA
				+ TagNode.ABRE + FLAVOR + TagNode.FECHA
				+ TagNode.ABRE + REQUIREMENTS + TagNode.FECHA
				+ TagNode.ABRE + REQUIREMENT + TagNode.FECHA2
				+ TagNode.ABRE2 + REQUIREMENTS + TagNode.FECHA
				+ TagNode.ABRE2 + FLAVOR + TagNode.FECHA
				+ TagNode.ABRE2 + FLAVORS + TagNode.FECHA;
		TagBuilder tagBuilder = new TagBuilder(FLAVORS);
//		TagNode parent = tagBuilder.getCurrentNode();
		
		for (int i = 0; i < 2; i++) {
//			tagBuilder.addToParent(parent, FLAVOR);
			tagBuilder.addToRoot(FLAVOR);
			tagBuilder.addChild(REQUIREMENTS);
			tagBuilder.addChild(REQUIREMENT);
		}
		String actualXml = tagBuilder.toXml();
		System.out.println(actualXml);
		assertEquals(expectedXml, actualXml);
	}

	@Test
	public void testBuildChildrenOfChildrenWithValues() {
		Charset charset = Charset.forName("ISO-8859-1");
		String stringEncode = null;
		try {
			stringEncode = URLEncoder.encode(MAE_TE_AMO, charset.name());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(stringEncode);
		
		String expectedXml = TagNode.ABRE + FLAVORS + TagNode.FECHA
				+ TagNode.ABRE + FLAVOR + TagNode.FECHA
				+ TagNode.ABRE + REQUIREMENTS + TagNode.FECHA
				+ TagNode.ABRE + REQUIREMENT + TagNode.FECHA
				+ MAE_TE_AMO
				+ TagNode.ABRE2 + REQUIREMENT + TagNode.FECHA
				+ TagNode.ABRE2 + REQUIREMENTS + TagNode.FECHA
				+ TagNode.ABRE2 + FLAVOR + TagNode.FECHA
				+ TagNode.ABRE + FLAVOR + TagNode.FECHA
				+ TagNode.ABRE + REQUIREMENTS + TagNode.FECHA
				+ TagNode.ABRE + REQUIREMENT + TagNode.FECHA
				+ MAE_TE_AMO
				+ TagNode.ABRE2 + REQUIREMENT + TagNode.FECHA
				+ TagNode.ABRE2 + REQUIREMENTS + TagNode.FECHA
				+ TagNode.ABRE2 + FLAVOR + TagNode.FECHA
				+ TagNode.ABRE2 + FLAVORS + TagNode.FECHA;
		TagBuilder tagBuilder = new TagBuilder(FLAVORS);
		TagNode parent = tagBuilder.getCurrentNode();
		
		for (int i = 0; i < 2; i++) {
			tagBuilder.addToParent(parent, FLAVOR);;
			tagBuilder.addChild(REQUIREMENTS);
//			tagBuilder.addChild(REQUIREMENT, stringEncode, charset);
			tagBuilder.addChild(REQUIREMENT, stringEncode);
		}
		String actualXml = tagBuilder.toXml();
		System.out.println(actualXml);
		System.out.println(expectedXml);
		assertEquals(expectedXml, actualXml);
	}
}

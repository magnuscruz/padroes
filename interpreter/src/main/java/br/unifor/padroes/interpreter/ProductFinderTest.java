package br.unifor.padroes.interpreter;

import static br.unifor.padroes.interpreter.modelo.ProductSize.GRANDE;
import static br.unifor.padroes.interpreter.modelo.ProductSize.MEDIO;
import static br.unifor.padroes.interpreter.modelo.ProductSize.NAO_SE_APLICA;
import static br.unifor.padroes.interpreter.modelo.ProductSize.PEQUENO;
import static java.awt.Color.PINK;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import br.unifor.padroes.builder.JsonBuilder;
import br.unifor.padroes.builder.ProductJsonBuilder;
import br.unifor.padroes.interpreter.modelo.Product;
import br.unifor.padroes.interpreter.modelo.ProductRepository;

public class ProductFinderTest {

	private ProductFinder finder;

	private Product fireTruck = new Product(1, "FIRE TRUCK", RED, 8.95, MEDIO);
	private Product barbieClassic = new Product(2, "BARBIE CLASSIC", YELLOW,
			15.95, PEQUENO);
	private Product frisbee = new Product(3, "FRISBEE", PINK, 9.99, GRANDE);
	private Product baseball = new Product(4, "BASEBALL", WHITE, 8.95,
			NAO_SE_APLICA);
	private Product toyConvertible = new Product(5, "TOY PORCHE CONVERTIBLE",
			RED, 230.00, NAO_SE_APLICA);

	@Before
	public void setUp() throws Exception {
//		JSONObject produtos = new JSONObject();
//		JSONArray list = new JSONArray();
//		produtos.put("produtos", list);
//
//		JSONObject obj = new JSONObject();
//		obj.put("id", new Integer(1));
//		obj.put("nome", "FIRE TRUCK");
//		obj.put("corInt", RED.getRGB());
//		obj.put("preco", new Double(8.95));
//		obj.put("tamanhoStr", MEDIO);
//		list.put(obj);
//
//		obj = new JSONObject();
//		obj.put("id", new Integer(2));
//		obj.put("nome", "BARBIE CLASSIC");
//		obj.put("corInt", YELLOW.getRGB());
//		obj.put("preco", new Double(15.95));
//		obj.put("tamanhoStr", MEDIO);
//		list.put(obj);
//
//		obj = new JSONObject();
//		obj.put("id", new Integer(3));
//		obj.put("nome", "FRISBEE");
//		obj.put("corInt", PINK.getRGB());
//		obj.put("preco", new Double(9.99));
//		obj.put("tamanhoStr", GRANDE);
//		list.put(obj);
//
//		obj = new JSONObject();
//		obj.put("id", new Integer(4));
//		obj.put("nome", "BASEBALL");
//		obj.put("corInt", WHITE.getRGB());
//		obj.put("preco", new Double(8.95));
//		obj.put("tamanhoStr", NAO_SE_APLICA);
//		list.put(obj);
//
//		obj = new JSONObject();
//		obj.put("id", new Integer(5));
//		obj.put("nome", "TOY PORCHE CONVERTIBLE");
//		obj.put("corInt", RED.getRGB());
//		obj.put("preco", new Double(230.00));
//		obj.put("tamanhoStr", NAO_SE_APLICA);
//		list.put(obj);
//
//		FileWriter file = new FileWriter("banco.json");
//		file.write(list.toString());
//		file.flush();
//		file.close();

		finder = new ProductFinder(createRepository());
	}

	private ProductRepository createRepository() throws Exception {
		FileInputStream fileReader = new FileInputStream("banco.json");
		byte[] array = new byte[512];
		StringBuffer buffer = new StringBuffer();
		while (fileReader.read(array)>-1) {
			buffer.append(new String(array));
		}
		ProductRepository productRepository = new ProductRepository();
		JsonBuilder<Product> builder = new ProductJsonBuilder();
		List<Product> list = builder.parser(new JSONArray(buffer.toString()));
		productRepository.addAll(list);

		fileReader.close();
		return productRepository;
	}

	@Test
	public void testFindByColor() {
		List<Product> list = finder.byColor(RED);
		assertEquals("ENCONTROU 2 PRODUTOS", 2, list.size());
		assertTrue("ACHOU FIRE TRUCK", list.contains(fireTruck));
		assertTrue("ACHOU PORCHE CONVERTIBLE", list.contains(toyConvertible));
	}

	@Test
	public void testFindByPrice() {
		List<Product> list = finder.byPrice(8.95);
		assertEquals("ENCONTROU 2 PRODUTOS", 2, list.size());
		for (Product product : list) {
			assertTrue(product.getPreco() == 8.95);
		}
	}

	@Test
	public void testFindByColorSizeAndBelowPrice() {
		List<Product> list = finder.byColorSizeAndBelowPrice(RED, PEQUENO,
				10.00);
		assertEquals("ENCONTROU 0 PRODUTOS", 0, list.size());
		list = finder.byColorSizeAndBelowPrice(RED, MEDIO, 10.00);
		assertTrue("LISTA NÃO VAZIA", !list.isEmpty()); 
		assertEquals("ENCONTROU 1 PRODUTO FIRE TRUCK", fireTruck, list.get(0));
	}

	@Test
	public void testFindByBelowPriceAndDifColor() {
		List<Product> list = finder.byBelowPriceAndDifColor(9.00, WHITE);
		assertEquals("ENCONTROU 1 PRODUTO NÃO BRANCO CUSTO < R$ 9.00", 1,
				list.size());
		assertTrue("ENCONTROU 1 PRODUTO FIRE TRUCK", list.contains(fireTruck));
		list = finder.byBelowPriceAndDifColor(9.00, RED);
		assertEquals("ENCONTROU 1 PRODUTO NÃO VERMELHO CUSTO < R$ 9.00", 1,
				list.size());
		assertTrue("ENCONTROU 1 PRODUTO BASEBALL", list.contains(baseball));
	}

}
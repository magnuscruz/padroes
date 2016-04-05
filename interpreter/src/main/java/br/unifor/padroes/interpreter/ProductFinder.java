package br.unifor.padroes.interpreter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.unifor.padroes.interpreter.modelo.Product;
import br.unifor.padroes.interpreter.modelo.ProductRepository;
import br.unifor.padroes.interpreter.modelo.ProductSize;

public class ProductFinder {

	private ProductRepository productRepository;

	public ProductFinder(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> byColor(Color cor) {
		Spec spec = new ColorSpec(cor);

		List<Product> list = new ArrayList<Product>();
		for (Product product : productRepository) {
			if (spec.isSatisfied(product)) {
				list.add(product);
			}
		}
		return list;
	}

	public List<Product> byPrice(Double preco) {
		Spec spec = new PriceSpec(preco);

		List<Product> list = new ArrayList<Product>();
		for (Product product : productRepository) {
			if (spec.isSatisfied(product)) {
				list.add(product);
			}
		}
		return list;
	}

	public List<Product> byColorSizeAndBelowPrice(Color cor,
			ProductSize tamanho, Double preco) {
		Spec spec1 = new ColorSpec(cor);
		Spec spec2 = new SizeSpec(tamanho);
		Spec spec3 = new BelowPriceSpec(preco);
		Spec andSpec = new AndSpec(spec1, spec2);

		Spec spec = new AndSpec(andSpec, spec3);

		List<Product> list = new ArrayList<Product>();
		for (Product product : productRepository) {
			if (spec.isSatisfied(product)) {
				list.add(product);
			}
		}
		return list;
	}

	public List<Product> byBelowPriceAndDifColor(Double preco, Color cor) {
		Spec belowPriceSpec = new BelowPriceSpec(preco);
		Spec colorSpec = new ColorSpec(cor);
		Spec notSpec = new NotSpec(colorSpec);

		Spec spec = new AndSpec(belowPriceSpec, notSpec);
		
		List<Product> list = new ArrayList<Product>();
		for (Product product : productRepository) {
			if (spec.isSatisfied(product)) {
				list.add(product);
			}
		}
		return list;
	}
}

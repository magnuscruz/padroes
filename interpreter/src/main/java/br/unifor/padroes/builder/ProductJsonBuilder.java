package br.unifor.padroes.builder;

import br.unifor.padroes.interpreter.modelo.Product;


public class ProductJsonBuilder extends JsonBuilder<Product> {

	private static ProductJsonBuilder builder;

	public static ProductJsonBuilder getInstance() {
		if (builder == null) {
			builder = new ProductJsonBuilder();
		}
		return builder;
	}

	@Override
	protected Product createEntidade() {
		return new Product();
	}
}

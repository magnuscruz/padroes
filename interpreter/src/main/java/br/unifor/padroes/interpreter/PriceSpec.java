package br.unifor.padroes.interpreter;

import br.unifor.padroes.interpreter.modelo.Product;

public class PriceSpec extends EqualsSpec {

	public PriceSpec(Object value) {
		super(value);
	}

	@Override
	protected Object getProductValue(Product produto) {
		return produto.getPreco();
	}

}

package br.unifor.padroes.interpreter;

import br.unifor.padroes.interpreter.modelo.Product;

public class BelowPriceSpec extends Spec {

	public BelowPriceSpec(Object value) {
		super(value);
	}

	@Override
	public boolean isSatisfied(Product produto) {
		return produto.getPreco().doubleValue() < new Double(getValue().toString());
	}
}

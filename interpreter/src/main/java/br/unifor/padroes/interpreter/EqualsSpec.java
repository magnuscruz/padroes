package br.unifor.padroes.interpreter;

import br.unifor.padroes.interpreter.modelo.Product;

public abstract class EqualsSpec extends Spec {

	public EqualsSpec(Object value) {
		super(value);
	}

	@Override
	public boolean isSatisfied(Product produto) {
		return getProductValue(produto).equals(getValue());
	}

	protected abstract Object getProductValue(Product produto);

}
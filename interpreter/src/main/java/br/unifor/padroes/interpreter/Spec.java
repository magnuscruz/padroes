package br.unifor.padroes.interpreter;

import br.unifor.padroes.interpreter.modelo.Product;

public abstract class Spec {
	private Object value;

	public Spec(Object value) {
		this.value = value;
	}

	public abstract boolean isSatisfied(Product produto);

	protected Object getValue() {
		return value;
	}
}

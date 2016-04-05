package br.unifor.padroes.interpreter;

import br.unifor.padroes.interpreter.modelo.Product;

public class ColorSpec extends EqualsSpec {

	public ColorSpec(Object value) {
		super(value);
	}

	public Object getProductValue(Product produto) {
		return produto.getCor();
	}

}

package br.unifor.padroes.interpreter;

import br.unifor.padroes.interpreter.modelo.Product;

public class SizeSpec extends EqualsSpec {

	public SizeSpec(Object value) {
		super(value);
	}

	public Object getProductValue(Product produto) {
		return produto.getTamanho();
	}

}

package br.unifor.padroes.interpreter;

import br.unifor.padroes.interpreter.modelo.Product;

public class NotSpec extends Spec {

	private Spec spec;

	public NotSpec(Spec spec) {
		super(null);
		this.spec = spec;
	}

	@Override
	public boolean isSatisfied(Product produto) {
		return !spec.isSatisfied(produto);
	}

}

package br.unifor.padroes.interpreter;

import br.unifor.padroes.interpreter.modelo.Product;

public class AndSpec extends Spec {
	private Spec spec1;
	private Spec spec2;
	public AndSpec(Spec spec1, Spec spec2) {
		super(null);
		this.spec1 = spec1;
		this.spec2 = spec2;
	}

	@Override
	public boolean isSatisfied(Product produto) {
		return spec1.isSatisfied(produto) && spec2.isSatisfied(produto);
	}
}

package br.unifor.padroes.interpreter.modelo;

public enum ProductSize {
	PEQUENO, MEDIO, GRANDE, NAO_SE_APLICA;
	public static ProductSize getProductSize(String value) {
		ProductSize[] values = values();
		for (ProductSize productSize : values) {
			if (productSize.toString().equals(value)) {
				return productSize;
			}
		}
		return NAO_SE_APLICA;
	}
}

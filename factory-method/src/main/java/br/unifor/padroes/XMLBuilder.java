package br.unifor.padroes;


public class XMLBuilder extends OutputBuilder {

	private static final String ABRE2 = "</";
	private static final String FECHA = ">";
	private static final String ABRE= "<";
	public XMLBuilder(String nome) {
		super(nome);
	}

	@Override
	protected String newElement(String name) {
		return openElement(name)+closeElement(name);
	}

	@Override
	protected int offsetBelow(String tagName) {
		return getDocument().lastIndexOf(closeElement(tagName));
	}

	@Override
	protected String openElement(String name) {
		return ABRE+name+FECHA;
	}

	@Override
	protected String closeElement(String name) {
		return ABRE2+name+FECHA;
	}

	@Override
	protected int offsetOpenAbove(String tagName) {
		return getDocument().indexOf(openElement(tagName));
	}

	@Override
	protected int offsetCloseAbove(String tagName) {
		String closeElement = closeElement(tagName);
		return getDocument().lastIndexOf(closeElement)+closeElement.length();
	}
}
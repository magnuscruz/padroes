package br.unifor.padroes;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class OutputBuilder {

	private String nome;
	private StringBuilder document;

	public OutputBuilder(String nome) {
		this.setNome(nome);
		setDocument(new StringBuilder());
		addRoot(nome);
	}

	protected abstract String newElement(String name);

	protected abstract String openElement(String name);

	protected abstract String closeElement(String name);

	protected abstract int offsetBelow(String tagName);

	protected int offsetBelow() {
		return offsetBelow(getNome());
	}

	protected abstract int offsetOpenAbove(String tagName);

	protected int offsetOpenAbove() {
		return offsetOpenAbove(getNome());
	}

	protected abstract int offsetCloseAbove(String tagName);

	protected int offsetCloseAbove() {
		return offsetCloseAbove(getNome());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	protected void addRoot(String root) {
		getDocument().append(newElement(root));
	}

	public void addBelow(String tagName) {
		addBelow(tagName, getNome());
	}

	public void addBelow(String tagName, String tagOther) {
		getDocument().insert(offsetBelow(tagOther), newElement(tagName));
	}

	public void addAbove(String tagName) {
		addAbove(tagName, getNome());
	}

	public void addAbove(String tagName, String tagOther) {
		getDocument().insert(offsetOpenAbove(tagOther), openElement(tagName));
		getDocument().insert(offsetCloseAbove(tagOther), closeElement(tagName));
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public StringBuilder getDocument() {
		return document;
	}

	public void setDocument(StringBuilder document) {
		this.document = document;
	}
}

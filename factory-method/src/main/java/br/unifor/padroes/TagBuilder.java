package br.unifor.padroes;
import org.apache.commons.lang3.builder.ToStringBuilder;



public class TagBuilder extends OutputBuilder {

	private TagNode rootNode;
	private TagNode currentNode;
	

	public TagBuilder(String nome) {
		super(nome);
	}

	protected void addRoot(String root) {
		rootNode = new TagNode(root);
		setCurrentNode(rootNode);
	}

	@Override
	public void setDocument(StringBuilder document) {
		super.setDocument(document);
	}

	@Override
	protected String newElement(String name) {
		setCurrentNode(new TagNode(name));
		return getCurrentNode().toString();
	}

	public void addChild(String tagName) {
		TagNode parentNode = getCurrentNode();
		newElement(tagName);
		parentNode.add(getCurrentNode());
		if (rootNode == null) {
			rootNode = parentNode;
		}
	}

	public void addChild(String tagName, String value) {
		addChild(tagName);
		currentNode.setValue(value);
	}

	public void addToParent(TagNode parent, String tagName) {
		setCurrentNode(new TagNode(tagName));
		parent.add(getCurrentNode());
	}

	public void addToRoot(String tagName) {
		addToParent(rootNode, tagName);	
	}

	public void addSibling(String tagName) {
		addToParent(getCurrentNode().getParent(), tagName);
	}
	
	@Override
	protected int offsetBelow(String tagName) {
		return getDocument().lastIndexOf(closeElement(tagName));
	}

	@Override
	protected String openElement(String name) {
		return TagNode.ABRE+name+TagNode.FECHA;
	}

	@Override
	protected String closeElement(String name) {
		return TagNode.ABRE2+name+TagNode.FECHA;
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
	
	public String toXml(){
		return rootNode.toString();
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public TagNode getCurrentNode() {
		return currentNode;
	}

	private void setCurrentNode(TagNode currentNode) {
		this.currentNode = currentNode;
	}
}

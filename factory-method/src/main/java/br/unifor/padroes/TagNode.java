package br.unifor.padroes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class TagNode {
	public static final String ABRE2 = "</";
	public static final String FECHA = ">";
	public static final String FECHA2 = "/>";
	public static final String ABRE = "<";
	private Map<String, String> attributes = new HashMap<String, String>();
	private String tagName;
	private TagNode parent;
	private List<TagNode> children = new ArrayList<TagNode>();
	private String value = "";

	public TagNode(String tagName) {
		this.tagName = tagName;
	}

	public void add(TagNode childNode) {
		children.add(childNode);
		childNode.setParent(this);
	}

	public void addAttribute(String name, String value) {
		attributes.put(name, value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		String strChildren = children.toString().replaceAll(", ", "")
				.replace("[", "").replace("]", "");
		return ABRE
				+ tagName
				+ (value.isEmpty() && children.isEmpty() ? FECHA2 : FECHA
						+ getValue() + strChildren + ABRE2 + tagName + FECHA);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public String getTagName() {
		return tagName;
	}

	public TagNode getParent() {
		return parent;
	}

	public void setParent(TagNode parent) {
		this.parent = parent;
	}
}

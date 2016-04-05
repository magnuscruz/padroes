package br.unifor.padroes;
import java.nio.charset.Charset;


public class DecoratorTagNode extends TagNode {

	private Charset charset;

	public DecoratorTagNode(String tagName, Charset charset) {
		super(tagName);
		this.setCharset(charset);
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}


}
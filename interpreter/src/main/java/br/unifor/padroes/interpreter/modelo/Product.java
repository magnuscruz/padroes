package br.unifor.padroes.interpreter.modelo;

import java.awt.Color;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Product {

	private Integer id;
	private String nome;
	private Color cor;
	private Double preco;
	private ProductSize tamanho;

	public Product(Integer id, String nome, Color cor, Double preco,
			ProductSize tamanho) {
		this.id = id;
		this.nome = nome;
		this.cor = cor;
		this.preco = preco;
		this.tamanho = tamanho;
	}

	public Product() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public Integer getCorInt() {
		return cor.getRGB();
	}

	public void setCorInt(Integer cor) {
		this.cor = Color.decode(""+cor);
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ProductSize getTamanho() {
		return tamanho;
	}

	public void setTamanho(ProductSize tamanho) {
		this.tamanho = tamanho;
	}

	public String getTamanhoStr() {
		return tamanho.toString();
	}

	public void setTamanhoStr(String tamanho) {
		this.tamanho = ProductSize.getProductSize(tamanho);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}

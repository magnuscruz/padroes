package br.blog.desenvolvimentoweb.service;

import java.io.Serializable;

public class Conta implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5372835109130066492L;

	private String email;

	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}

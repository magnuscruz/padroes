package br.blog.desenvolvimentoweb.util;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import br.blog.desenvolvimentoweb.service.Conta;

@Named
@SessionScoped
public class ComponentSession implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 8975123428609846923L;

	private Conta conta;


	public ComponentSession() {
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Boolean isLogado() {
		return conta != null;
	}

	public void informarConta(Conta contaExistente) {
		this.conta = contaExistente;
	}

	public void sair() {
		this.conta = null;
	}
}

package br.unifor.padroes.web;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import br.unifor.padroes.entidades.Usuario;

@Named
@SessionScoped
public class ControleUsuarioLogado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9025423453689284825L;
	private Usuario conta;

	public ControleUsuarioLogado() {
	}

	public Usuario getConta() {
		return conta;
	}

	public void setConta(Usuario conta) {
		this.conta = conta;
	}

	public Boolean isLogado() {
		return conta != null;
	}

	public void informarConta(Usuario contaExistente) {
		this.conta = contaExistente;
	}

	public void sair() {
		this.conta = null;
	}
}

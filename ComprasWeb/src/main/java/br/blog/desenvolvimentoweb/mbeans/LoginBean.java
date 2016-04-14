package br.blog.desenvolvimentoweb.mbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.blog.desenvolvimentoweb.service.Conta;
import br.blog.desenvolvimentoweb.service.ContaService;
import br.blog.desenvolvimentoweb.util.ComponentSession;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4060981446284363818L;

	@Inject
	private ContaService service;

	@Inject
	private ComponentSession session;

	private Conta conta;

	public String index() {
		return "login";
	}

	public String efetuarLogin() {
		Conta contaExistente = service.obterConta(conta);
		if (contaExistente == null) {
			FacesContext.getCurrentInstance().addMessage("email", new FacesMessage("Conta não encontrada com o email informado em nosso sistema"));
			return null;
		}
		try {
			service.validarCredenciais(contaExistente, conta);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(e.getMessage()));
			return null;
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ola", "Login efetuado com sucesso"));
		session.setConta(contaExistente);
		return "sucesso";
	}

	public String sair() {
		session.sair();
		return "index";
	}

	public Conta getConta() {
		if (conta == null) {
			conta = new Conta();
		}
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}

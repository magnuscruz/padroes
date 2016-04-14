package br.unifor.padroes.web.mbean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.unifor.padroes.entidades.Usuario;
import br.unifor.padroes.services.UsuarioService;
import br.unifor.padroes.web.ControleUsuarioLogado;
import br.unifor.padroes.web.util.FacesUtil;

public class LoginBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4060981446284363818L;

	@Inject
	private UsuarioService service;

	@Inject
	private ControleUsuarioLogado session;

	private Usuario conta;

	public String index() {
		return "login";
	}

	public String efetuarLogin() {
		List<Usuario> contaExistente = service.buscarPorFiltro(conta);
		if (contaExistente == null || contaExistente.isEmpty()) {
			FacesUtil
					.addErrorMessage("Conta não encontrada com o email informado em nosso sistema");
			return null;
		}
		Usuario usuario = contaExistente.get(0);
		try {
			service.validarCredenciais(usuario, conta);
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
			return null;
		}
		FacesUtil.addInfoMessage(
						"Login efetuado com sucesso");
		session.setConta(usuario);
		return "sucesso";
	}

	public String sair() {
		session.sair();
		return "index";
	}

	public Usuario getConta() {
		if (conta == null) {
			conta = new Usuario();
		}
		return conta;
	}

	public void setConta(Usuario conta) {
		this.conta = conta;
	}

}

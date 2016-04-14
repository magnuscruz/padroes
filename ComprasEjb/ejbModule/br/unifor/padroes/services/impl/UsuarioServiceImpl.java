package br.unifor.padroes.services.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.unifor.padroes.dao.UsuarioDao;
import br.unifor.padroes.entidades.Usuario;
import br.unifor.padroes.services.UsuarioService;

/**
 * Session Bean implementation class UsuarioServiceImpl
 */
@Stateless(mappedName = "UsuarioService", name = "UsuarioService")
public class UsuarioServiceImpl extends
		AbstractServiceImpl<Usuario, Usuario, Integer> implements
		UsuarioService {

	@Inject
	private UsuarioDao dao;

	protected UsuarioDao getDao() {
		return dao;
	}

	@Override
	public void validarCredenciais(Usuario usuario1, Usuario usuario2) {
		if (!usuario1.getLogin().equals(usuario2.getLogin())
				|| !usuario1.getSenha().equals(usuario2.getSenha())) {
			throw new RuntimeException("Login ou Senha inválidos!");
		}

	}

	@Override
	protected Class<Usuario> getEntidadeClass() {
		return Usuario.class;
	}
}

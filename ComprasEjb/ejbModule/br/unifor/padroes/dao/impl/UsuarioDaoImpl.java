package br.unifor.padroes.dao.impl;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import br.unifor.padroes.dao.UsuarioDao;
import br.unifor.padroes.entidades.Usuario;

@Named("UsuarioDao")
public class UsuarioDaoImpl extends AbstractDao<Usuario, Usuario, Integer>
		implements UsuarioDao {

	@SuppressWarnings("unchecked")
	@Override
	protected void extrairFiltros(Usuario filtro,
			CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Root<Usuario> root) {
		String nome = filtro.getNome();

		EntityType<Usuario> model = entityManager.getMetamodel().entity(Usuario.class);
		if (nome != null) {
			criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.upper(root
					.get((SingularAttribute<Usuario, String>) model
							.getSingularAttribute("nome"))), "%" + nome.toUpperCase() + "%"));
		}

		String login = filtro.getLogin();

		if (login != null) {
			criteriaQuery.where(criteriaBuilder.equal(root
					.get((SingularAttribute<Usuario, String>) model
							.getSingularAttribute("login")), login));
		}
	}

}

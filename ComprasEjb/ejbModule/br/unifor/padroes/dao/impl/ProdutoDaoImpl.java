package br.unifor.padroes.dao.impl;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import br.unifor.padroes.dao.ProdutoDao;
import br.unifor.padroes.entidades.Produto;

@Named("ProdutoDao")
public class ProdutoDaoImpl extends AbstractDao<Produto, Produto, Integer>
		implements ProdutoDao {

	@SuppressWarnings("unchecked")
	@Override
	protected void extrairFiltros(Produto filtro,
			CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Root<Produto> root) {
		String nome = filtro.getNome();

		EntityType<Produto> model = entityManager.getMetamodel().entity(Produto.class);
		if (nome != null) {
			criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.upper(root
					.get((SingularAttribute<Produto, String>) model
							.getSingularAttribute("nome"))), "%" + nome.toUpperCase() + "%"));
		}

		Double valor = filtro.getValor();

		if (valor != null) {
			criteriaQuery.where(criteriaBuilder.equal(root
					.get((SingularAttribute<Produto, Double>) model
							.getSingularAttribute("valor")), valor));
		}
	}

}

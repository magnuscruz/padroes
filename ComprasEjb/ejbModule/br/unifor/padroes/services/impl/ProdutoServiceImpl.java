package br.unifor.padroes.services.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.unifor.padroes.dao.ProdutoDao;
import br.unifor.padroes.entidades.Produto;
import br.unifor.padroes.services.ProdutoService;

/**
 * Session Bean implementation class ProdutoServiceImpl
 */
@Stateless(mappedName="ProdutoService", name="ProdutoService")
public class ProdutoServiceImpl extends AbstractServiceImpl<Produto, Produto, Integer> implements ProdutoService {

	@Inject
    private ProdutoDao dao;


	protected ProdutoDao getDao() {
		return dao;
	}

	@Override
	protected Class<Produto> getEntidadeClass() {
		return Produto.class;
	}
}

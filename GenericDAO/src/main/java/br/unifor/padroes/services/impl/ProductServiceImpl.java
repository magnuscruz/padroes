package br.unifor.padroes.services.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.unifor.padroes.dao.impl.ProductDAO;
import br.unifor.padroes.entities.Product;
import br.unifor.padroes.entitymanager.SimpleEntityManager;
import br.unifor.padroes.services.ProductService;

@Stateless(mappedName="ProductService")
public class ProductServiceImpl implements ProductService {
	private ProductDAO dao;

	private SimpleEntityManager simpleEntityManager;

	public ProductServiceImpl(){
		this(new SimpleEntityManager("PadraoPU"));
	}
	
	public ProductServiceImpl(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		dao = new ProductDAO(simpleEntityManager.getEntityManager());
	}

	/* (non-Javadoc)
	 * @see br.unifor.padroes.services.ProductServiceI#save(br.unifor.padroes.entities.Product)
	 */
	public void save(Product product) {
		try {
			simpleEntityManager.beginTransaction();
			dao.save(product);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
	}

	/* (non-Javadoc)
	 * @see br.unifor.padroes.services.ProductServiceI#findAll()
	 */
	public List<Product> findAll() {
		return dao.findAll();
	}
}
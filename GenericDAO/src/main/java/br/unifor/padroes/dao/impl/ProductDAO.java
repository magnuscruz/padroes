package br.unifor.padroes.dao.impl;
 
import javax.persistence.EntityManager;

import br.unifor.padroes.dao.GenericDAO;
import br.unifor.padroes.entities.Product;

public class ProductDAO extends GenericDAO<Long, Product> {
    public ProductDAO(EntityManager entityManager) {
        super(entityManager);
    }
}


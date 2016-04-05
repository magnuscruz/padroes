package br.unifor.padroes.dao.impl;
 
import javax.persistence.EntityManager;

import br.unifor.padroes.dao.GenericDAO;
import br.unifor.padroes.entities.Customer;
 
public class CustomerDAO extends GenericDAO<Long, Customer> {
    public CustomerDAO(EntityManager entityManager) {
        super(entityManager);
    }
}
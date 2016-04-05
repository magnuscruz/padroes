package br.unifor.padroes.services;

import java.util.List;

import br.unifor.padroes.dao.impl.CustomerDAO;
import br.unifor.padroes.entities.Customer;
import br.unifor.padroes.entitymanager.SimpleEntityManager;

public class CustomerService {
	private CustomerDAO dao;

	private SimpleEntityManager simpleEntityManager;

	public CustomerService(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		dao = new CustomerDAO(simpleEntityManager.getEntityManager());
	}

	public void save(Customer costumer) {
		try {
			simpleEntityManager.beginTransaction();
			//costumer.validate();
			dao.save(costumer);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
	}

	public List<Customer> findAll() {
		return dao.findAll();
	}
}

package br.unifor.padroes.services;

import java.util.List;

import javax.ejb.Remote;

import br.unifor.padroes.entities.Product;

@Remote
public interface ProductService {

	public abstract void save(Product product);

	public abstract List<Product> findAll();

}
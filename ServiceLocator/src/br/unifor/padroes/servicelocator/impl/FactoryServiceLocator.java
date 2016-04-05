package br.unifor.padroes.servicelocator.impl;

import br.unifor.padroes.servicelocator.AbstractServiceLocator;
import br.unifor.padroes.servicelocator.ServiceLocator;

/**
 * @author Magnus Alencar da Cruz
 *
 */
public abstract class FactoryServiceLocator { 
	/**
	 * Instancia de forma generica o Service Locator a partir do nome do ResourceBundle especifica.
	 * @param nomeServer
	 * @return
	 */
	public static ServiceLocator getInstance(final String nomeServer) {
	
		//ConcreteCreator � sobrescreve o factory method e 
		//retorna um objeto da classe ConcreteProduct;
		ServiceLocator serviceLocator = new AbstractServiceLocator() {
			@Override
			protected String getResourceBundle() {
				return nomeServer;
			}
		};
		return serviceLocator;
	}
}

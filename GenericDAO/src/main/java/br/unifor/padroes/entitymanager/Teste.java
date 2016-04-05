package br.unifor.padroes.entitymanager;

import br.unifor.padroes.servicelocator.ServiceLocator;
import br.unifor.padroes.services.ProductService;

public class Teste {

	public static void main(String[] args) {
		try {
			ProductService service = (ProductService) ServiceLocator
					.getInstance().buscarEJB(ProductService.class);

			service.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

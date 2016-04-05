package br.unifor.padroes.servicelocator;

import java.io.Serializable;

/**
 * Interface Service Locator que encapsula a localização e instanciação dos serviços.
 * @author Magnus Alencar da Cruz
 */
public interface ServiceLocator {
	Serializable getService(Class serviceClass);
	Serializable getService(Class serviceClass, String nameService);
}

package br.unifor.padroes.servicelocator;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Creator � declara o factory method (m�todo de fabrica��o) que retorna o
 * objeto da classe Product (produto). Este elemento tamb�m pode definir uma
 * implementa��o b�sica que retorna um objeto de uma classe ConcreteProduct
 * (produto concreto) b�sica;
 * 
 * O padr�o Factory Method, da forma como foi descrito no livro Design Patterns:
 * Elements of Reusable Object-Oriented Software, cont�m os seguintes elementos:
 * 
 * @author Magnus Alencar da Cruz
 *
 */
public abstract class AbstractServiceLocator implements ServiceLocator {

	private Properties props = new Properties();
	private static Hashtable<String, InitialContext> cache = new Hashtable<String, InitialContext>();
	protected InitialContext contexto = null;

	{
		String nomeContext = getResourceBundle();
		if ((contexto = cache.get(nomeContext)) == null) {
			ResourceBundle bundle = ResourceBundle.getBundle(nomeContext);
			Enumeration<String> keys = bundle.getKeys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = bundle.getString(key);
				props.put(key, value);
			}
			try {
				contexto = new InitialContext(props);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

	protected abstract String getResourceBundle();

	/**
	 * FactoryMethod:
	 * 
	 * @param serviceClass (Product) � define uma interface para os objetos criados pelo
	 * factory method;
	 * 
	 * @return Serializable (ConcreteProduct) � uma implementa��o para a interface
	 * Product.
	 * 
	 */
	public Serializable getService(Class serviceClass) {

		try {
			return (Serializable) contexto.lookup(serviceClass.getSimpleName()
					+ "#" + serviceClass.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Sobrecarga do FactoryMethod:
	 * 
	 * @param serviceClass (Product) � define uma interface para os objetos criados pelo
	 * factory method;
	 * @param nameService - Nome do servi�os a ser localizado.
	 * @return Serializable (ConcreteProduct) � uma implementa��o para a interface
	 * Product.
	 */
	public Serializable getService(Class serviceClass, String nameService) {

		try {
			return (Serializable) contexto.lookup(nameService + "#"
					+ serviceClass.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

}

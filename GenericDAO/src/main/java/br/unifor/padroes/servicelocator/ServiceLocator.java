package br.unifor.padroes.servicelocator;

import java.util.Properties;
import javax.naming.InitialContext;
/**
 * Classe utilizada para fazer um lookup generico do EJB.
 */
public class ServiceLocator {
  /**
   * Propriedades do Glassfish.
   */
  private static Properties properties = null;
  /**
   * Este ServiceLocator será um Singleton
   */
  private static ServiceLocator instance;
 /**
   * Recupera a instancia do Singleton
   */
  public static ServiceLocator getInstance() {
    if (instance == null) {
      instance = new ServiceLocator();
    }
    return instance;
  }
  /**
   * Monta um objeto Properties com as informações para localizar o glassfish.
     * @return
     */
    private static Properties getProperties() {
      if (properties == null) {
        properties = new Properties();
        properties.put("org.omg.CORBA.ORBInitialHost", "localhost");
        properties.put("org.omg.CORBA.ORBInitialPort", "3700");
      }
      return properties;
    }
    /**
     * Método que faz o lookup generico de um EJB.
     * @param clazz - Classe que representa a interface do EJB que será feito
     * o lookup.
     */
    public static <T> T buscarEJB(Class<T> clazz) throws Exception {
      /* Cria o initial context que faz via JNDI a procura do EJB. */
      InitialContext ctx = new InitialContext(getProperties());
      /* Pega o nome completo da interface utilizando reflection, faz o lookup
      do EJB e o retorno do EJB. */
      return (T) ctx.lookup(clazz.getSimpleName()+"#"+clazz.getName());
    }

}



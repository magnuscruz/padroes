//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2016.02.23 às 09:43:55 PM GMT-03:00 
//


package br.unifor.models;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.unifor.models package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Market_QNAME = new QName("http://www.example.org/market", "market");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.unifor.models
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MarketType }
     * 
     */
    public MarketType createMarketType() {
        return new MarketType();
    }

    /**
     * Create an instance of {@link OrderType }
     * 
     */
    public OrderType createOrderType() {
        return new OrderType();
    }

    /**
     * Create an instance of {@link OrdersType }
     * 
     */
    public OrdersType createOrdersType() {
        return new OrdersType();
    }

    /**
     * Create an instance of {@link CustomerType }
     * 
     */
    public CustomerType createCustomerType() {
        return new CustomerType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarketType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/market", name = "market")
    public JAXBElement<MarketType> createMarket(MarketType value) {
        return new JAXBElement<MarketType>(_Market_QNAME, MarketType.class, null, value);
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.12.30 at 02:13:41 AM EET 
//


package com.concretepage.gs_ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wishlistInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wishlistInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="wishlistID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="clientId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="bookISBN" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="titlu" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wishlistInfo", propOrder = {
    "wishlistID",
    "clientId",
    "bookISBN",
    "titlu",
    "price"
})
public class WishlistInfo {

    protected int wishlistID;
    protected int clientId;
    @XmlElement(required = true)
    protected String bookISBN;
    @XmlElement(required = true)
    protected String titlu;
    protected int price;

    /**
     * Gets the value of the wishlistID property.
     * 
     */
    public int getWishlistID() {
        return wishlistID;
    }

    /**
     * Sets the value of the wishlistID property.
     * 
     */
    public void setWishlistID(int value) {
        this.wishlistID = value;
    }

    /**
     * Gets the value of the clientId property.
     * 
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets the value of the clientId property.
     * 
     */
    public void setClientId(int value) {
        this.clientId = value;
    }

    /**
     * Gets the value of the bookISBN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookISBN() {
        return bookISBN;
    }

    /**
     * Sets the value of the bookISBN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookISBN(String value) {
        this.bookISBN = value;
    }

    /**
     * Gets the value of the titlu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitlu() {
        return titlu;
    }

    /**
     * Sets the value of the titlu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitlu(String value) {
        this.titlu = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(int value) {
        this.price = value;
    }

}

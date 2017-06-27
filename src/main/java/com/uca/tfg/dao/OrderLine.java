package com.uca.tfg.dao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "orderLine")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderLine implements Serializable {

	private static final long serialVersionUID = -4745195410724554377L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderLineID", unique = true, nullable = false)
	private long id;
	@Column(name = "orderLineQuantity", unique = false, nullable = false)
	private int quantity;
	@OneToOne
	private Product product;
	@ManyToOne
	private Order order;

	public OrderLine() {
	}

	public OrderLine(Product product, int quantity) {
		super();
		this.setProduct(product);
		this.setQuantity(quantity);
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

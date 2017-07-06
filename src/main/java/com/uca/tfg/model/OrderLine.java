package com.uca.tfg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	private Order order;

	public OrderLine() {
	}

	public OrderLine(Product product, int quantity, Order order) {
		this.setProduct(product);
		this.setQuantity(quantity);
		this.setOrder(order);
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return this.order;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

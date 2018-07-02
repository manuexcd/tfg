package com.uca.tfg.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "orderTable")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order implements Serializable {
	
	private enum OrderStatus {RECEIVED, IN_PROGRESS, IN_DELIVERY, DELIVERED}
	
	private static final long serialVersionUID = 6892693125355139371L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderID", unique = true, nullable = false)
	private long id;
	@Column(name = "orderTotalPrice", unique = false, nullable = false)
	private double totalPrice;
	@Column(name = "orderDate", unique = false, nullable = false)
	//@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp date;
	@Column(name = "orderStatus", unique = false, nullable = false)
	private OrderStatus orderStatus = OrderStatus.RECEIVED;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<OrderLine> orderLines;
	@ManyToOne
	@JsonManagedReference
	private User user;

	public Order() {

	}

	public Order(Timestamp date, User user) {
		super();
		this.setDate(date);
		this.setTotalPrice(0);
		this.setUser(user);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<OrderLine> getOrderLines() {
		return this.orderLines;
	}

	public void setOrderLines(Collection<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public void updatePrice() {
		double total = 0;
		for (OrderLine orderLine : this.orderLines) {
			total += (orderLine.getProduct().getPrice() * orderLine.getQuantity());
		}
		this.setTotalPrice(total);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order ID: " + this.getId() + ". Date: " + this.getDate().toString() + "\n");
		sb.append(this.getOrderLines().toString());

		return sb.toString();
	}
}

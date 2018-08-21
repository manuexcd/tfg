package com.uca.tfg.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "orderTable")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NamedQuery(name = "Order.findByParam", query = "SELECT o FROM Order o WHERE o.id = ?1")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Order implements Serializable {
	
	private enum OrderStatus {
		RECEIVED, IN_PROGRESS, IN_DELIVERY, DELIVERED
	}

	private static final long serialVersionUID = 6892693125355139371L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderID", unique = true, nullable = false)
	private long id;
	@Column(name = "orderTotalPrice", unique = false, nullable = false)
	private double totalPrice = 0;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = CustomDateSerializer.class)
	@NonNull
	@Column(name = "orderDate", unique = false, nullable = false)
	private Date date;
	@Column(name = "orderStatus", unique = false, nullable = false)
	private OrderStatus orderStatus = OrderStatus.RECEIVED;
	@ToString.Exclude
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<OrderLine> orderLines;
	@ManyToOne
	@JsonManagedReference
	@ToString.Exclude
	@NonNull
	private User user;

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

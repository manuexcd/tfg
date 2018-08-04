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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "order_table")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
	@ToString.Exclude
	@Column(name = "orderID", unique = true, nullable = false)
	private long id;
	@Column(name = "orderTotalPrice", unique = false, nullable = false)
	private double totalPrice = 0;
	@Column(name = "orderDate", unique = false, nullable = false)
	// @Temporal(TemporalType.TIMESTAMP)
	@NonNull
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp date;
	@Column(name = "orderStatus", unique = false, nullable = false)
	private OrderStatus orderStatus = OrderStatus.RECEIVED;
	@ToString.Exclude
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<OrderLine> orderLines;
	@NonNull
	@ToString.Exclude
	@ManyToOne
	private User user;

	public void updatePrice() {
		double total = 0;
		for (OrderLine orderLine : this.orderLines) {
			total += (orderLine.getProduct().getPrice() * orderLine.getQuantity());
		}
		this.setTotalPrice(total);
	}
}

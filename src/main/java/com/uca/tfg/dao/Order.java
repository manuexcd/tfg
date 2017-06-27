package com.uca.tfg.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "orderTable")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order implements Serializable {

	private static final long serialVersionUID = 6892693125355139371L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderID", unique = true, nullable = false)
	private long id;
	@Column(name = "orderTotalPrice", unique = false, nullable = false)
	private double totalPrice;
	@Column(name = "orderDate", unique = false, nullable = false)
	private Date date;
	@OneToMany(mappedBy = "order")
	private List<OrderLine> orderLines;
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;

	public Order() {
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

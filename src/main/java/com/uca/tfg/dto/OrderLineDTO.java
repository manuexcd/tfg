package com.uca.tfg.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.Product;

import lombok.Data;
import lombok.ToString;

@Data
public class OrderLineDTO implements Serializable {
	private static final long serialVersionUID = 4745195410724554377L;
	@ToString.Exclude
	private long id;
	private Product product;
	private int quantity;
	@ToString.Exclude
	@JsonIgnore
	private Order order;
}

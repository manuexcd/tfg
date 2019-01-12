package com.uca.tfg.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.Product;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderLineDTO implements Serializable {
	private static final long serialVersionUID = 4745195410724554377L;
	@ToString.Exclude
	private long id;
	@NonNull
	private Product product;
	@NonNull
	private int quantity;
	@NonNull
	@ToString.Exclude
	@JsonIgnore
	private Order order;
}

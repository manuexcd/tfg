package com.uca.tfg.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.uca.tfg.model.CustomDateSerializer;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderDTO implements Serializable {

	private enum OrderStatus {
		RECEIVED, IN_PROGRESS, IN_DELIVERY, DELIVERED
	}

	private static final long serialVersionUID = 6892693125355139371L;
	@ToString.Exclude
	private long id;
	private double totalPrice = 0;
	@NonNull
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp date;
	private OrderStatus orderStatus = OrderStatus.RECEIVED;
	@ToString.Exclude
	private Collection<OrderLine> orderLines;
	@ToString.Exclude
	private User user;
}
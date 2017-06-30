package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.dao.Order;
import com.uca.tfg.dao.OrderLine;

public interface OrderLineManager {

	public Collection<OrderLine> getAllOrderLines();

	public ResponseEntity<OrderLine> getOneOrderLine(long id);

	// public Collection<OrderLine> getOrderLines(Order order);

	public OrderLine addOrderLine(OrderLine orderLine);

	public ResponseEntity<OrderLine> deleteOrderLine(long id);
}

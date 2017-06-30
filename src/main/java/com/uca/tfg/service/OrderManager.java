package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.dao.Order;
import com.uca.tfg.dao.OrderLine;

public interface OrderManager {

	public Collection<Order> getAllOrders();
	
	public Collection<OrderLine> getOrderLines(long id);

	public ResponseEntity<Order> getOrder(long id);
	
	public OrderLine addOrderLine(long id, long idProduct, int n);

	public ResponseEntity<Order> deleteOrder(long id);
}

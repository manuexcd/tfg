package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.exceptions.OrderNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;

public interface OrderManager {

	public Collection<Order> getAllOrders();
	
	public Collection<OrderLine> getOrderLines(long id) throws OrderNotFoundException;

	public ResponseEntity<Order> getOrder(long id);
	
	public OrderLine addOrderLine(long id, long idProduct, int n) throws Exception;

	public ResponseEntity<Order> deleteOrder(long id);
}

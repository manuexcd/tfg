package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uca.tfg.exception.NoStockException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;

public interface OrderManager {

	public Page<Order> getAllOrders(Pageable page);
	
	public Page<Order> getAllOrdersByOrderStatus(Pageable page);
	
	public Page<Order> getAllOrdersByDate(Pageable page);
	
	public Page<Order> getOrdersByUser(long userId, Pageable page) throws UserNotFoundException;
	
	public Collection<OrderLine> getOrderLines(long id) throws OrderNotFoundException;

	public Order getOrder(long id) throws OrderNotFoundException;
	
	public Order createOrder(Order order);
	
	public OrderLine addOrderLine(long id, long idProduct, int n) throws NoStockException, ProductNotFoundException, OrderNotFoundException;

	public void deleteOrder(long id);
	
	public Order getTemporalOrder() throws OrderNotFoundException;
}

package com.uca.tfg.service;

import java.util.Collection;

import com.uca.tfg.exception.NoStockException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;

public interface OrderManager {

	public Collection<Order> getAllOrders();
	
	public Collection<Order> getAllOrdersByOrderStatus();
	
	public Collection<Order> getAllOrdersByDate();
	
	public Collection<Order> getOrdersByUser(long userId);
	
	public Collection<OrderLine> getOrderLines(long id) throws OrderNotFoundException;

	public Order getOrder(long id);
	
	public OrderLine addOrderLine(long id, long idProduct, int n) throws NoStockException, ProductNotFoundException, OrderNotFoundException;

	public void deleteOrder(long id);
}

package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;

public interface OrderService {

	public Page<Order> getAllOrders(Pageable page);

	public Page<Order> getAllOrdersByOrderStatus(Pageable page);

	public Page<Order> getAllOrdersByDate(Pageable page);

	public Page<Order> getOrdersByUser(long userId, Pageable page) throws UserNotFoundException;

	public Collection<OrderLine> getOrderLines(long id) throws OrderNotFoundException;

	public Order getOrder(long id) throws OrderNotFoundException;

	public Order createTemporalOrder(Order order);

	public Order getTemporalOrder() throws OrderNotFoundException;

	public Order confirmTemporalOrder(Order order) throws OrderNotFoundException;

	public Order updateOrder(Order order);

	public Order cancelOrder(long id) throws OrderNotFoundException;

	public void deleteOrder(long id);
}

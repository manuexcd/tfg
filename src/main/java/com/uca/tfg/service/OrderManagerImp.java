package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.Order;
import com.uca.tfg.dao.OrderDAO;
import com.uca.tfg.dao.OrderLine;
import com.uca.tfg.dao.ProductDAO;

@Service("orderManager")
public class OrderManagerImp implements OrderManager {

	@Autowired
	private OrderDAO orders;

	@Autowired
	private ProductDAO products;

	public Collection<Order> getAllOrders() {
		return orders.findAll();
	}

	public ResponseEntity<Order> getOrder(long id) {
		Order order = orders.findOne(id);

		if (order != null)
			return new ResponseEntity<>(order, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public Collection<OrderLine> getOrderLines(long id) {
		Order order = orders.findOne(id);

		if (order != null)
			return order.getOrderLines();
		else
			return null;
	}

	public OrderLine addOrderLine(long id, long idProduct, int n) {
		Order order = orders.getOne(id);
		OrderLine line = new OrderLine(products.findOne(idProduct), n);
		
		if (line != null) {
			order.getOrderLines().add(line);
			order.updatePrice();
		}

		orders.save(order);

		return line;
	}

	public ResponseEntity<Order> deleteOrder(long id) {
		Order order = orders.findOne(id);

		if (order != null) {
			order.getOrderLines().clear();
			orders.delete(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		}

		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

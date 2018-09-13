package com.uca.tfg.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.exceptions.OrderNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.service.OrderManager;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderManager orderManager;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Order> getAllOrders() {
		return orderManager.getAllOrdersByDate();
	}
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public Collection<Order> getAllOrdersByOrderStatus() {
		return orderManager.getAllOrdersByOrderStatus();
	}
	
	@RequestMapping(value="/user/{userId}", method = RequestMethod.GET)
	public Collection<Order> getOrdersByUser(@PathVariable long userId) {
		return orderManager.getOrdersByUser(userId);
	}

	@RequestMapping(value = "/{id}/lines", method = RequestMethod.GET)
	public Collection<OrderLine> getOrderLines(@PathVariable long id) throws OrderNotFoundException {
		return orderManager.getOrderLines(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> getOrder(@PathVariable long id) {
		Order order = orderManager.getOrder(id);

		if (order != null)
			return new ResponseEntity<>(order, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}/{idProduct}-{n}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public OrderLine addOrderLine(@PathVariable long id, @PathVariable long idProduct, @PathVariable int n)
			throws Exception {
		return orderManager.addOrderLine(id, idProduct, n);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Order> deleteOrder(@PathVariable long id) {
		Order order = orderManager.getOrder(id);

		if (order != null) {
			orderManager.deleteOrder(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

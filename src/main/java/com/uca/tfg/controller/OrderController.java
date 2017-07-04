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

import com.uca.tfg.dao.Order;
import com.uca.tfg.dao.OrderLine;
import com.uca.tfg.exceptions.OrderNotFoundException;
import com.uca.tfg.service.OrderManager;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderManager orderManager;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Order> getAllOrders() {
		return orderManager.getAllOrders();
	}

	@RequestMapping(value = "/{id}/lines", method = RequestMethod.GET)
	public Collection<OrderLine> getOrderLines(@PathVariable long id) throws OrderNotFoundException {
		return orderManager.getOrderLines(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> getOrder(@PathVariable long id) {
		return orderManager.getOrder(id);
	}

	@RequestMapping(value = "/{id}/{idProduct}-{n}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public OrderLine addOrderLine(@PathVariable long id, @PathVariable long idProduct, @PathVariable int n) throws Exception {
		return orderManager.addOrderLine(id, idProduct, n);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Order> deleteOrder(@PathVariable long id) {
		return orderManager.deleteOrder(id);
	}
}

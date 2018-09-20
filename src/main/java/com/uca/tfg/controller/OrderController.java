package com.uca.tfg.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.exception.NoStockException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.service.OrderManager;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderManager orderManager;

	@GetMapping
	public ResponseEntity<Collection<Order>> getAllOrders() {
		return new ResponseEntity<>(orderManager.getAllOrdersByDate(), HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<Collection<Order>> getAllOrdersByOrderStatus() {
		return new ResponseEntity<>(orderManager.getAllOrdersByOrderStatus(), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Collection<Order>> getOrdersByUser(@PathVariable long userId) {
		Collection<Order> orders = orderManager.getOrdersByUser(userId);
		if (orders != null)
			return new ResponseEntity<>(orders, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}/lines")
	public ResponseEntity<Collection<OrderLine>> getOrderLines(@PathVariable long id) throws OrderNotFoundException {
		Collection<OrderLine> orderLines = orderManager.getOrderLines(id);
		if (orderLines != null)
			return new ResponseEntity<>(orderLines, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable long id) {
		Order order = orderManager.getOrder(id);
		if (order != null)
			return new ResponseEntity<>(order, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/{id}/{idProduct}-{n}")
	public ResponseEntity<OrderLine> addOrderLine(@PathVariable long id, @PathVariable long idProduct,
			@PathVariable int n) throws NoStockException, ProductNotFoundException, OrderNotFoundException {
		return new ResponseEntity<>(orderManager.addOrderLine(id, idProduct, n), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable long id) {
		Order order = orderManager.getOrder(id);

		if (order != null) {
			orderManager.deleteOrder(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

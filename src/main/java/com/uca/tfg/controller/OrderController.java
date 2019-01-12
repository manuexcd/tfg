package com.uca.tfg.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.exception.NoStockException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.model.Constants;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.service.OrderManager;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderManager orderManager;

	@GetMapping
	public ResponseEntity<Page<Order>> getAllOrders(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(orderManager.getAllOrdersByDate(PageRequest.of(page, pageSize)), HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<Page<Order>> getAllOrdersByOrderStatus(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(orderManager.getAllOrdersByOrderStatus(PageRequest.of(page, pageSize)),
				HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Page<Order>> getOrdersByUser(@PathVariable long userId,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		Page<Order> orders = orderManager.getOrdersByUser(userId, PageRequest.of(page, pageSize));
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
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

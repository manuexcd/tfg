package com.uca.tfg.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.model.OrderLine;
import com.uca.tfg.service.OrderLineManager;

@RestController
@RequestMapping(value = "/orderLines")
public class OrderLineController {

	@Autowired
	private OrderLineManager orderLineManager;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<OrderLine> getAllOrderLines() {
		return orderLineManager.getAllOrderLines();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderLine> getOneOrderLine(@PathVariable long id) {
		OrderLine orderLine = orderLineManager.getOrderLine(id);
		if (orderLine != null)
			return new ResponseEntity<>(orderLine, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<OrderLine> deleteOrderLine(@PathVariable long id) {
		OrderLine orderLine = orderLineManager.getOrderLine(id);
		if (orderLine != null) {
			orderLineManager.deleteOrderLine(id);
			return new ResponseEntity<>(orderLine, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

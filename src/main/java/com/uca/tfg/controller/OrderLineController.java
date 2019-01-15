package com.uca.tfg.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.exception.OrderLineNotFoundException;
import com.uca.tfg.model.Constants;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.service.OrderLineManager;

@RestController
@RequestMapping(value = Constants.PATH_ORDERLINES)
public class OrderLineController {

	@Autowired
	private OrderLineManager orderLineManager;

	@GetMapping
	public ResponseEntity<Collection<OrderLine>> getAllOrderLines() {
		return new ResponseEntity<>(orderLineManager.getAllOrderLines(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderLine> getOneOrderLine(@PathVariable long id) throws OrderLineNotFoundException {
		OrderLine orderLine = orderLineManager.getOrderLine(id);
		if (orderLine != null)
			return new ResponseEntity<>(orderLine, HttpStatus.OK);
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<OrderLine> deleteOrderLine(@PathVariable long id) throws OrderLineNotFoundException {
		OrderLine orderLine = orderLineManager.getOrderLine(id);
		if (orderLine != null) {
			orderLineManager.deleteOrderLine(id);
			return new ResponseEntity<>(orderLine, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

package com.uca.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.exception.OrderLineNotFoundException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.model.Constants;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.service.OrderLineService;

@RestController
@RequestMapping(value = Constants.PATH_ORDERLINES)
public class OrderLineController {

	@Autowired
	private OrderLineService orderLineService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderLine> getOneOrderLine(@PathVariable long id) {
		try {
			return new ResponseEntity<>(orderLineService.getOrderLine(id), HttpStatus.OK);
		} catch (OrderLineNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<OrderLine> deleteOrderLine(@PathVariable long id) {
		try {
			orderLineService.deleteOrderLine(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (OrderLineNotFoundException | OrderNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

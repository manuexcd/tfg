package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.OrderLine;
import com.uca.tfg.dao.OrderLineDAO;

@Service("orderLineManager")
public class OrderLineManagerImp implements OrderLineManager {

	@Autowired
	private OrderLineDAO orderLines;

	public Collection<OrderLine> getAllOrderLines() {
		return orderLines.findAll();
	}

	public ResponseEntity<OrderLine> getOneOrderLine(long id) {
		OrderLine orderLine = orderLines.findOne(id);

		if (orderLine != null)
			return new ResponseEntity<>(orderLine, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<OrderLine> deleteOrderLine(long id) {
		OrderLine orderLine = orderLines.findOne(id);

		if (orderLine != null) {
			orderLines.delete(id);
			return new ResponseEntity<>(orderLine, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}

package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.tfg.exception.OrderLineNotFoundException;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.repository.OrderLineRepository;

@Service("orderLineManager")
public class OrderLineManagerImp implements OrderLineManager {

	@Autowired
	private OrderLineRepository orderLines;

	public Collection<OrderLine> getAllOrderLines() {
		return orderLines.findAll();
	}

	public OrderLine getOrderLine(long id) throws OrderLineNotFoundException {
		return orderLines.findById(id).orElseThrow(OrderLineNotFoundException::new);
	}

	public void deleteOrderLine(long id) {
		orderLines.deleteById(id);
	}
}
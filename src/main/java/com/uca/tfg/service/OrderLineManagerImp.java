package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.OrderLineDAO;
import com.uca.tfg.model.OrderLine;

@Service("orderLineManager")
public class OrderLineManagerImp implements OrderLineManager {

	@Autowired
	private OrderLineDAO orderLines;

	public Collection<OrderLine> getAllOrderLines() {
		return orderLines.findAll();
	}

	public OrderLine getOrderLine(long id) {
		return orderLines.findOne(id);
	}

	public OrderLine deleteOrderLine(long id) {
		OrderLine orderLine = orderLines.findOne(id);

		if (orderLine != null) {
			orderLines.delete(id);
			return orderLine;
		} else
			return null;
	}
}
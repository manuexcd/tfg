package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.OrderLineDAO;
import com.uca.tfg.model.OrderLine;

@Service("orderLineManager")
@DependsOn(value = { "orderManager", "productManager" })
public class OrderLineManagerImp implements OrderLineManager {

	@Autowired
	private OrderLineDAO orderLines;
	
	public Collection<OrderLine> getAllOrderLines() {
		return orderLines.findAll();
	}

	public OrderLine getOrderLine(long id) {
		return orderLines.findById(id).orElse(null);
	}

	public OrderLine deleteOrderLine(long id) {
		OrderLine orderLine = orderLines.findById(id).orElse(null);

		if (orderLine != null) {
			orderLines.delete(orderLine);
			return orderLine;
		} else
			return null;
	}
}
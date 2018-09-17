package com.uca.tfg.service;

import java.util.Collection;

import com.uca.tfg.model.OrderLine;

public interface OrderLineManager {

	public Collection<OrderLine> getAllOrderLines();

	public OrderLine getOrderLine(long id);

	public void deleteOrderLine(long id);
}

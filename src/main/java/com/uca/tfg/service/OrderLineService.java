package com.uca.tfg.service;

import com.uca.tfg.exception.OrderLineNotFoundException;
import com.uca.tfg.model.OrderLine;

public interface OrderLineService {

	public OrderLine getOrderLine(long id) throws OrderLineNotFoundException;

	public void deleteOrderLine(long id) throws OrderLineNotFoundException;
}

package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.model.OrderLine;

public interface OrderLineManager {

	public Collection<OrderLine> getAllOrderLines();

	public ResponseEntity<OrderLine> getOneOrderLine(long id);

	public ResponseEntity<OrderLine> deleteOrderLine(long id);
}

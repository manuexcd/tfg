package com.uca.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.tfg.exception.OrderLineNotFoundException;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.repository.OrderLineRepository;

@Service("orderLineManager")
public class OrderLineServiceImpl implements OrderLineService {

	@Autowired
	private OrderLineRepository repository;

	public OrderLine getOrderLine(long id) throws OrderLineNotFoundException {
		return repository.findById(id).orElseThrow(OrderLineNotFoundException::new);
	}

	public void deleteOrderLine(long id) throws OrderLineNotFoundException {
		if (repository.existsById(id))
			repository.deleteById(id);
		else
			throw new OrderLineNotFoundException();
	}
}
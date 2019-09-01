package com.uca.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.tfg.exception.OrderLineNotFoundException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.repository.OrderLineRepository;

@Service("orderLineManager")
public class OrderLineServiceImpl implements OrderLineService {

	@Autowired
	private OrderLineRepository repository;

	@Autowired
	private OrderService orderService;

	public OrderLine getOrderLine(long id) throws OrderLineNotFoundException {
		return repository.findById(id).orElseThrow(OrderLineNotFoundException::new);
	}

	public void deleteOrderLine(long id) throws OrderLineNotFoundException, OrderNotFoundException {
		if (repository.existsById(id)) {
			OrderLine line = repository.getOne(id);
			Order order = orderService.getOrder(line.getOrder().getId());
			order.getOrderLines().remove(line);
			orderService.updateOrder(order);
			repository.deleteById(id);
		} else
			throw new OrderLineNotFoundException();
	}
}
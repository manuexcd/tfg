package com.uca.tfg.service;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.Product;
import com.uca.tfg.repository.OrderRepository;
import com.uca.tfg.repository.OrderLineRepository;
import com.uca.tfg.repository.ProductRepository;

@Service("orderLineManager")
@DependsOn(value = { "orderManager", "productManager" })
public class OrderLineManagerImp implements OrderLineManager {

	@Autowired
	private OrderLineRepository orderLines;

	@Autowired
	private ProductRepository products;

	@Autowired
	private OrderRepository orders;

	@PostConstruct
	public void init() {
		if (orderLines.findAll().isEmpty()) {
			Optional<Product> product1 = products.findByName("PlayStation 4");
			Optional<Product> product2 = products.findByName("Xbox");
			Optional<Order> order1 = orders.findById((long) 1);
			Optional<Order> order2 = orders.findById((long) 2);
			if (product1.isPresent() && order1.isPresent())
				orderLines.save(new OrderLine(product1.get(), 2, order1.get()));
			if (product2.isPresent() && order2.isPresent())
				orderLines.save(new OrderLine(product2.get(), 2, order2.get()));
		}
	}

	public Collection<OrderLine> getAllOrderLines() {
		return orderLines.findAll();
	}

	public OrderLine getOrderLine(long id) {
		return orderLines.findById(id).orElse(null);
	}

	public void deleteOrderLine(long id) {
		orderLines.deleteById(id);
	}
}
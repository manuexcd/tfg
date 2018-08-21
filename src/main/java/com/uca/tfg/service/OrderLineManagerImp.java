package com.uca.tfg.service;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.OrderDAO;
import com.uca.tfg.dao.OrderLineDAO;
import com.uca.tfg.dao.ProductDAO;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;

@Service("orderLineManager")
@DependsOn(value = { "orderManager", "productManager" })
public class OrderLineManagerImp implements OrderLineManager {

	@Autowired
	private OrderLineDAO orderLines;

	@Autowired
	private OrderDAO orders;

	@Autowired
	private ProductDAO products;

	@PostConstruct
	public void init() {
		if (orderLines.findAll().isEmpty()) {
			System.out.println("PostConstruct ORDERLINES");
			orderLines.save(new OrderLine(3, products.findById((long) 1).get(), orders.findById((long) 2).get()));
			orderLines.save(new OrderLine(2, products.findById((long) 2).get(), orders.findById((long) 2).get()));
			orderLines.save(new OrderLine(1, products.findById((long) 1).get(), orders.findById((long) 3).get()));
			orderLines.save(new OrderLine(1, products.findById((long) 2).get(), orders.findById((long) 3).get()));
			orderLines.save(new OrderLine(1, products.findById((long) 3).get(), orders.findById((long) 3).get()));
			orderLines.save(new OrderLine(5, products.findById((long) 4).get(), orders.findById((long) 4).get()));

			for (Order order : orders.findAll()) {
				order.updatePrice();
				orders.save(order);
			}
		}
	}

	public Collection<OrderLine> getAllOrderLines() {
		return orderLines.findAll();
	}

	public OrderLine getOrderLine(long id) {
		return orderLines.findById(id).get();
	}

	public OrderLine deleteOrderLine(long id) {
		OrderLine orderLine = orderLines.findById(id).get();

		if (orderLine != null) {
			orderLines.delete(orderLine);
			return orderLine;
		} else
			return null;
	}
}
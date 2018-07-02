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
		if(orderLines.findAll().isEmpty()) {
			System.out.println("PostConstruct ORDERLINES");
			orderLines.save(new OrderLine(products.findOne((long) 1), 3, orders.findOne((long) 2)));
			orderLines.save(new OrderLine(products.findOne((long) 2), 2, orders.findOne((long) 2)));
			orderLines.save(new OrderLine(products.findOne((long) 1), 1, orders.findOne((long) 3)));
			orderLines.save(new OrderLine(products.findOne((long) 2), 1, orders.findOne((long) 3)));
			orderLines.save(new OrderLine(products.findOne((long) 3), 1, orders.findOne((long) 3)));
			orderLines.save(new OrderLine(products.findOne((long) 4), 5, orders.findOne((long) 4)));
			
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
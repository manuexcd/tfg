package com.uca.tfg.service;

import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.OrderDAO;
import com.uca.tfg.dao.OrderLineDAO;
import com.uca.tfg.dao.ProductDAO;
import com.uca.tfg.dao.UserDAO;
import com.uca.tfg.exceptions.NoStockException;
import com.uca.tfg.exceptions.OrderNotFoundException;
import com.uca.tfg.exceptions.ProductNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.Product;
import com.uca.tfg.model.User;

@Service("orderManager")
@DependsOn("userManager")
public class OrderManagerImp implements OrderManager {

	@Autowired
	private OrderDAO orders;

	@Autowired
	private OrderLineDAO orderLines;

	@Autowired
	private ProductDAO products;

	@Autowired
	private UserDAO users;

	@PostConstruct
	public void init() {
		if (orders.findAll().isEmpty()) {
			System.out.println("PostConstruct ORDERS");
			orders.save(new Order(new Date(), users.findById((long) 1).get()));
			orders.save(new Order(new Date(), users.findById((long) 1).get()));
			orders.save(new Order(new Date(), users.findById((long) 2).get()));
			orders.save(new Order(new Date(), users.findById((long) 3).get()));
		}
	}

	public Collection<Order> getAllOrders() {
		return orders.findAll();
	}

	public Collection<Order> getAllOrdersByOrderStatus() {
		return orders.findAllByOrderByOrderStatus();
	}

	public Collection<Order> getOrdersByParam(String param) {
		return orders.findByParam(Long.valueOf(param));
	}

	public Order getOrder(long id) {
		return orders.findById(id).get();
	}

	public Collection<Order> getOrdersByUser(long userId) {
		User user = users.findById(userId).get();

		return orders.findByUser(user);
	}

	public Collection<OrderLine> getOrderLines(long id) throws OrderNotFoundException {
		Order order = orders.findById(id).get();

		if (order != null)
			return order.getOrderLines();
		else
			throw new OrderNotFoundException();
	}

	public OrderLine addOrderLine(long id, long idProduct, int n) throws Exception {
		Order order = orders.getOne(id);
		Product product = products.findById(idProduct).get();
		if (order != null) {
			if (product != null) {
				if (product.getStockAvaiable() >= n) {
					OrderLine line = new OrderLine(n, product, order);
					order.getOrderLines().add(line);
					order.updatePrice();
					product.updateStock(n);
					products.save(product);
					orderLines.save(line);
					orders.save(order);

					return line;
				} else
					throw new NoStockException();
			} else
				throw new ProductNotFoundException();
		} else
			throw new OrderNotFoundException();
	}

	public Order deleteOrder(long id) {
		Order order = orders.findById(id).get();

		if (order != null) {
			orders.delete(order);
			return order;
		} else
			return null;
	}
}

package com.uca.tfg.service;

import java.sql.Timestamp;
import java.util.Collection;

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
			orders.save(new Order(new Timestamp(System.currentTimeMillis() - 1000), users.findById((long) 1).orElse(null)));
			orders.save(new Order(new Timestamp(System.currentTimeMillis() - 2500), users.findById((long) 1).orElse(null)));
			orders.save(new Order(new Timestamp(System.currentTimeMillis() - 5000), users.findById((long) 2).orElse(null)));
			orders.save(new Order(new Timestamp(System.currentTimeMillis() - 10000), users.findById((long) 3).orElse(null)));
		}
	}

	public Collection<Order> getAllOrders() {
		return orders.findAll();
	}
	
	public Collection<Order> getAllOrdersByOrderStatus() {
		return orders.findAllByOrderByOrderStatus();
	}
	
	public Collection<Order> getAllOrdersByDate() {
		return orders.findAllByOrderByDate();
	}
	
	public Order getOrder(long id) {
		return orders.findById(id).orElse(null);
	}
	
	public Collection<Order> getOrdersByUser(long userId) {
		User user = users.findById(userId).orElse(null);
		
		return orders.findByUser(user);
	}

	public Collection<OrderLine> getOrderLines(long id) throws OrderNotFoundException {
		Order order = orders.findById(id).orElse(null);

		if (order != null)
			return order.getOrderLines();
		else
			throw new OrderNotFoundException();
	}

	public OrderLine addOrderLine(long id, long idProduct, int n) throws Exception {
		Order order = orders.getOne(id);
		Product product = products.findById(idProduct).orElse(null);
		if (order != null) {
			if (product != null) {
				if (product.getStockAvailable() >= n) {
					OrderLine line = new OrderLine(product, n, order);
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
		Order order = orders.findById(id).orElse(null);

		if (order != null) {
			orders.delete(order);
			return order;
		} else
			return null;
	}
}

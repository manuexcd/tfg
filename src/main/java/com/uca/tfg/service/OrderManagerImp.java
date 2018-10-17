package com.uca.tfg.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.OrderDAO;
import com.uca.tfg.dao.OrderLineDAO;
import com.uca.tfg.dao.ProductDAO;
import com.uca.tfg.dao.UserDAO;
import com.uca.tfg.exception.NoStockException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.ProductNotFoundException;
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
			Optional<User> user1 = users.findById((long) 1);
			Optional<User> user2 = users.findById((long) 2);
			if (user1.isPresent())
				orders.save(new Order(new Timestamp(System.currentTimeMillis()), user1.get()));
			if (user2.isPresent())
				orders.save(new Order(new Timestamp(System.currentTimeMillis()), user2.get()));
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

	public OrderLine addOrderLine(long id, long idProduct, int n)
			throws NoStockException, ProductNotFoundException, OrderNotFoundException {
		Order order = orders.findById(id).orElse(null);
		Product product = products.findById(idProduct).orElse(null);
		if (order != null) {
			if (product != null) {
				if (product.getStockAvailable() >= n) {
					product.updateStock(n);
					OrderLine line = new OrderLine(product, n, order);
					if (order.getOrderLines() != null)
						order.getOrderLines().add(line);
					else
						order.setOrderLines(Arrays.asList(line));
					order.updatePrice();
					orderLines.saveAndFlush(line);
					products.save(product);
					orders.save(order);

					return line;
				} else
					throw new NoStockException();
			} else
				throw new ProductNotFoundException();
		} else
			throw new OrderNotFoundException();
	}

	public void deleteOrder(long id) {
		orders.deleteById(id);
	}
}

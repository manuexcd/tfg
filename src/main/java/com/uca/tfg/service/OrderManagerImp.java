package com.uca.tfg.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uca.tfg.exception.NoStockException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.Product;
import com.uca.tfg.model.User;
import com.uca.tfg.repository.OrderLineRepository;
import com.uca.tfg.repository.OrderRepository;
import com.uca.tfg.repository.ProductRepository;
import com.uca.tfg.repository.UserRepository;

@Service("orderManager")
@DependsOn("userManager")
public class OrderManagerImp implements OrderManager {

	@Autowired
	private OrderRepository orders;

	@Autowired
	private OrderLineRepository orderLines;

	@Autowired
	private ProductRepository products;

	@Autowired
	private UserRepository users;

	public Page<Order> getAllOrders(Pageable page) {
		return orders.findAll(page);
	}

	public Page<Order> getAllOrdersByOrderStatus(Pageable page) {
		return orders.findAllByOrderByOrderStatus(page);
	}

	public Page<Order> getAllOrdersByDate(Pageable page) {
		return orders.findAllByOrderByDate(page);
	}

	public Order getOrder(long id) {
		return orders.findById(id).orElse(null);
	}

	public Page<Order> getOrdersByUser(long userId, Pageable page) {
		User user = users.findById(userId).orElse(null);

		return orders.findByUser(user, page);
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

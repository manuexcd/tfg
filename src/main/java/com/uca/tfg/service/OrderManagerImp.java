package com.uca.tfg.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uca.tfg.exception.NoStockException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.OrderStatus;
import com.uca.tfg.model.Product;
import com.uca.tfg.repository.OrderRepository;
import com.uca.tfg.repository.ProductRepository;
import com.uca.tfg.repository.UserRepository;

@Service("orderManager")
public class OrderManagerImp implements OrderManager {

	@Autowired
	private OrderRepository orders;

	@Autowired
	private ProductRepository products;

	@Autowired
	private UserRepository users;
	
	@Override
	public Page<Order> getAllOrders(Pageable page) {
		return orders.findAll(page);
	}

	@Override
	public Page<Order> getAllOrdersByOrderStatus(Pageable page) {
		return orders.findAllByOrderByOrderStatus(page);
	}

	@Override
	public Page<Order> getAllOrdersByDate(Pageable page) {
		return orders.findAllByOrderByDate(page);
	}

	@Override
	public Order getOrder(long id) throws OrderNotFoundException {
		return orders.findById(id).orElseThrow(OrderNotFoundException::new);
	}

	@Override
	public Page<Order> getOrdersByUser(long userId, Pageable page) throws UserNotFoundException {
		return orders.findByUser(users.findById(userId).orElseThrow(UserNotFoundException::new), page);
	}

	@Override
	public Collection<OrderLine> getOrderLines(long id) throws OrderNotFoundException {
		return orders.findById(id).map(Order::getOrderLines).orElseThrow(OrderNotFoundException::new);
	}

	@Override
	public Order getTemporalOrder() throws OrderNotFoundException {
		return Optional.ofNullable(orders.findByOrderStatus(OrderStatus.TEMPORAL))
				.filter(orders -> !orders.isEmpty()).map(orders -> orders.get(0))
				.orElseThrow(OrderNotFoundException::new);
	}

	@Override
	public Order createOrder(Order order) {
		order.setDate(new Timestamp(System.currentTimeMillis()));
		order.getOrderLines().stream().forEach(line -> {
			try {
				Product product = products.findById(line.getProduct().getId())
						.orElseThrow(ProductNotFoundException::new);
				if (product.getStockAvailable() >= line.getQuantity()) {
					product.updateStock(line.getQuantity());
				}
				else {
					throw new NoStockException();
				}
				line.setOrder(order);
			} catch (ProductNotFoundException | NoStockException e) {
				order.getOrderLines().remove(line);
			}
		});
		order.updatePrice();
		return orders.save(order);
	}

	@Override
	public OrderLine addOrderLine(long id, long idProduct, int n)
			throws NoStockException, ProductNotFoundException, OrderNotFoundException {
		return null;
	}

	@Override
	public void deleteOrder(long id) {
		orders.deleteById(id);
	}
}

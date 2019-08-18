package com.uca.tfg.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uca.tfg.exception.NoStockException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.mail.MailSender;
import com.uca.tfg.model.Constants;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.Product;
import com.uca.tfg.repository.OrderRepository;
import com.uca.tfg.repository.ProductRepository;
import com.uca.tfg.repository.UserRepository;

@Service("orderManager")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orders;

	@Autowired
	private ProductRepository products;

	@Autowired
	private UserRepository users;

	@Autowired
	private MailSender mailSender;

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
		return Optional.ofNullable(orders.findByOrderStatus(Constants.ORDER_STATUS_TEMPORAL))
				.filter(orders -> !orders.isEmpty()).map(orders -> orders.get(0))
				.orElseThrow(OrderNotFoundException::new);
	}

	@Override
	public Order createTemporalOrder(Order order) {
		order.setDate(new Timestamp(System.currentTimeMillis()));
		order.setOrderStatus(Constants.ORDER_STATUS_TEMPORAL);
		order.getOrderLines().stream().forEach(line -> line.setOrder(order));
		order.updatePrice();
		return orders.save(order);
	}

	@Override
	public Order confirmTemporalOrder(Order entity) throws OrderNotFoundException {
		return Optional.ofNullable(entity).map(order -> {
			order.setDate(new Timestamp(System.currentTimeMillis()));
			order.setOrderStatus(Constants.ORDER_STATUS_RECEIVED);
			order.getOrderLines().stream().forEach(line -> {
				try {
					Product product = products.findById(line.getProduct().getId())
							.orElseThrow(ProductNotFoundException::new);
					if (product.getStockAvailable() >= line.getQuantity()) {
						product.updateStock(line.getQuantity());
					} else {
						throw new NoStockException();
					}
					line.setOrder(order);
				} catch (ProductNotFoundException | NoStockException e) {
					order.getOrderLines().remove(line);
				}
			});
			order.updatePrice();
			Map<Object, Object> params = new HashMap<Object, Object>();
			params.put(Constants.TEMPLATE_PARAM_ORDER_ID, order.getId());
			params.put(Constants.TEMPLATE_PARAM_ORDER_PRICE, order.getTotalPrice());
			params.put(Constants.TEMPLATE_PARAM_ORDER_LINES, order.getOrderLines());
			mailSender.sendEmail(order.getUser().getEmail(), Constants.SUBJECT_ORDER_CONFIRMED,
					Constants.TEMPLATE_ORDER_CONFIRMED, params);
			return orders.save(order);
		}).orElseThrow(OrderNotFoundException::new);
	}

	@Override
	public Order updateOrder(Order order) {
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put(Constants.TEMPLATE_PARAM_ORDER_ID, order.getId());
		params.put(Constants.TEMPLATE_PARAM_ORDER_STATUS, order.getOrderStatus());
		mailSender.sendEmail(order.getUser().getEmail(), Constants.SUBJECT_ORDER_UPDATED,
				Constants.TEMPLATE_ORDER_UPDATED, params);
		return orders.save(order);
	}

	@Override
	public Order cancelOrder(long id) throws OrderNotFoundException {
		Optional<Order> optional = orders.findById(id);
		if (optional.isPresent()) {
			Order order = optional.get();
			if (order.getOrderStatus().equals(Constants.ORDER_STATUS_RECEIVED)) {
				order.setOrderStatus(Constants.ORDER_STATUS_CANCELLED);
				Map<Object, Object> params = new HashMap<Object, Object>();
				params.put(Constants.TEMPLATE_PARAM_ORDER_ID, order.getId());
				mailSender.sendEmail(order.getUser().getEmail(), Constants.SUBJECT_ORDER_CANCELLED,
						Constants.TEMPLATE_ORDER_CANCELLED, params);
			}
			return orders.save(order);
		} else {
			throw new OrderNotFoundException();
		}
	}

	@Override
	public void deleteOrder(long id) {
		orders.deleteById(id);
	}
}

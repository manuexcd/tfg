package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.Order;
import com.uca.tfg.dao.OrderDAO;
import com.uca.tfg.dao.OrderLine;
import com.uca.tfg.dao.OrderLineDAO;
import com.uca.tfg.dao.Product;
import com.uca.tfg.dao.ProductDAO;
import com.uca.tfg.exceptions.NoStockException;
import com.uca.tfg.exceptions.OrderNotFoundException;
import com.uca.tfg.exceptions.ProductNotFoundException;

@Service("orderManager")
public class OrderManagerImp implements OrderManager {

	@Autowired
	private OrderDAO orders;

	@Autowired
	private OrderLineDAO orderLines;

	@Autowired
	private ProductDAO products;

	public Collection<Order> getAllOrders() {
		return orders.findAll();
	}

	public ResponseEntity<Order> getOrder(long id) {
		Order order = orders.findOne(id);

		if (order != null)
			return new ResponseEntity<>(order, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public Collection<OrderLine> getOrderLines(long id) throws OrderNotFoundException {
		Order order = orders.findOne(id);

		if (order != null)
			return order.getOrderLines();
		else
			throw new OrderNotFoundException();
	}

	public OrderLine addOrderLine(long id, long idProduct, int n) throws Exception {
		Order order = orders.getOne(id);
		Product product = products.findOne(idProduct);
		if (order != null) {
			if (product != null) {
				if (product.getStockAvaiable() >= n) {
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

	public ResponseEntity<Order> deleteOrder(long id) {
		Order order = orders.findOne(id);

		if (order != null) {
			orders.delete(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

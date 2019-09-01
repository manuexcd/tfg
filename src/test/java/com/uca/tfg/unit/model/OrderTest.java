package com.uca.tfg.unit.model;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

	@Test
	public void updatePrice() {
		Order order = new Order();
		Product product = new Product();
		product.setPrice(100);
		OrderLine orderLine = new OrderLine();
		orderLine.setProduct(product);
		orderLine.setQuantity(2);
		order.setOrderLines(Arrays.asList(orderLine));
		order.updatePrice();
		assertTrue(order.getTotalPrice() == 200);
	}
}

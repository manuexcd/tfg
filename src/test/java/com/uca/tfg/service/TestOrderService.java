package com.uca.tfg.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.uca.tfg.dao.OrderDAO;
import com.uca.tfg.model.Order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class TestOrderService {
	
	@Mock
	private OrderDAO orders;

	@InjectMocks
	private OrderManager service = new OrderManagerImp();
	
	@Test
	public void testGetAllOrders() {
		when(service.getAllOrders()).thenReturn(new ArrayList<Order>());
		Collection<Order> order = service.getAllOrders();
		Assert.assertNotNull(order);
	}
	
	@Test
	public void testGetOrderById() {
		when(service.getOrder(1)).thenReturn(new Order());
		assertNotNull(service.getOrder(1));
	}
	
	@Test
	public void testOrderNotFound() {
		when(service.getOrder(0)).thenReturn(null);
		assertNull(service.getOrder(0));
	}
	
	@Test
	public void testGetOrderName() {
		Order order = mock(Order.class);
		when(order.getTotalPrice()).thenReturn(12.5);
		assertEquals(12.5, order.getTotalPrice(), 2);
	}
}
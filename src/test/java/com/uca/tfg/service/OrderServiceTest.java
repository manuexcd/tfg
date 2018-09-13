package com.uca.tfg.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.model.Order;

@RunWith(SpringRunner.class)
public class OrderServiceTest {
	
	@MockBean
	private OrderManager service;
	
	@Test
	public void testGetAllOrders() {
		given(service.getAllOrders()).willReturn(Arrays.asList(new Order()));
		assertNotNull(service.getAllOrders());
	}
	
	@Test
	public void testGetOrderById() {
		given(service.getOrder(Mockito.anyLong())).willReturn(Optional.of(new Order()).get());
		assertNotNull(service.getOrder(Mockito.anyLong()));
	}
	
	@Test
	public void testOrderNotFound() {
		given(service.getOrder(Mockito.anyLong())).willReturn(null);
		assertNull(service.getOrder(Mockito.anyLong()));
	}
	
	/*@Test
	public void testGetOrderName() {
		Order order = mock(Order.class);
		when(order.getTotalPrice()).thenReturn(12.5);
		assertEquals(12.5, order.getTotalPrice(), 2);
	}*/
}
package com.uca.tfg.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.uca.tfg.exception.OrderLineNotFoundException;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.repository.OrderLineRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderLineServiceTest {

	@Mock
	private OrderLineRepository dao;

	@InjectMocks
	private OrderLineServiceImpl service;

	@Test
	public void testGetOrderLineById() throws OrderLineNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.of(new OrderLine()));
		assertNotNull(service.getOrderLine(anyLong()));
	}

	@Test(expected = OrderLineNotFoundException.class)
	public void testOrderLineNotFound() throws OrderLineNotFoundException {
		assertNotNull(service.getOrderLine(anyLong()));
	}

	@Test
	public void testDeleteOrderLine() throws OrderLineNotFoundException {
		OrderLine orderLine = new OrderLine();
		dao.save(orderLine);
		long id = orderLine.getId();
		given(dao.existsById(id)).willReturn(true);
		service.deleteOrderLine(id);
		given(dao.existsById(id)).willReturn(false);
		assertFalse(dao.existsById(id));
	}
	
	@Test(expected = OrderLineNotFoundException.class)
	public void testDeleteOrderLineNotFound() throws OrderLineNotFoundException {
		given(dao.existsById(anyLong())).willReturn(false);
		service.deleteOrderLine((long) 1);
	}
}

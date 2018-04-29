package com.uca.tfg.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.uca.tfg.dao.OrderLineDAO;
import com.uca.tfg.model.OrderLine;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class TestOrderLineService {

	@Mock
	private OrderLineDAO orders;

	@InjectMocks
	private OrderLineManager service = new OrderLineManagerImp();

	@Test
	public void testGetAllOrderLines() {
		when(service.getAllOrderLines()).thenReturn(new ArrayList<OrderLine>());
		Collection<OrderLine> order = service.getAllOrderLines();
		Assert.assertNotNull(order);
	}

	@Test
	public void testGetOrderLineById() {
		when(service.getOrderLine(1)).thenReturn(new OrderLine());
		assertNotNull(service.getOrderLine(1));
	}

	@Test
	public void testOrderLineNotFound() {
		when(service.getOrderLine(0)).thenReturn(null);
		assertNull(service.getOrderLine(0));
	}
}
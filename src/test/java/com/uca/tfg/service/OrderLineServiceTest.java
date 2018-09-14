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

import com.uca.tfg.model.OrderLine;

@RunWith(SpringRunner.class)
public class OrderLineServiceTest {
	
	@MockBean
	private OrderLineManager service;
	
	@Test
	public void testGetAllOrderLines() {
		given(service.getAllOrderLines()).willReturn(Arrays.asList(new OrderLine()));
		assertNotNull(service.getAllOrderLines());
	}

	@Test
	public void testGetOrderLineById() {
		given(service.getOrderLine(Mockito.anyLong())).willReturn(Optional.of(new OrderLine()).get());
		assertNotNull(service.getOrderLine(Mockito.anyLong()));
	}

	@Test
	public void testOrderLineNotFound() {
		given(service.getOrderLine(Mockito.anyLong())).willReturn(null);
		assertNull(service.getOrderLine(Mockito.anyLong()));
	}
}

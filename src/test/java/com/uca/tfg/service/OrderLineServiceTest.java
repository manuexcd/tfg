package com.uca.tfg.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.uca.tfg.exception.OrderLineNotFoundException;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.repository.OrderLineRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderLineServiceTest {
	
	@Mock
	private OrderLineRepository dao;
	
	@InjectMocks
	private OrderLineManagerImp service;
	
	@Test
	public void testGetAllOrderLines() {
		given(dao.findAll()).willReturn(Arrays.asList(new OrderLine()));
		assertNotNull(service.getAllOrderLines());
	}

	@Test
	public void testGetOrderLineById() throws OrderLineNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.of(new OrderLine()));
		assertNotNull(service.getOrderLine(anyLong()));
	}

	@Test(expected = OrderLineNotFoundException.class)
	public void testOrderLineNotFound() throws OrderLineNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNotNull(service.getOrderLine(anyLong()));
	}
	
	@Test
	public void testDeleteOrderLine() {
		OrderLine orderLine = new OrderLine();
		dao.save(orderLine);
		long id = orderLine.getId();
		service.deleteOrderLine(id);
		assertFalse(dao.existsById(id));
	}
}

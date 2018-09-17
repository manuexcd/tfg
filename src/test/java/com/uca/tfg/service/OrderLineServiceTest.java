package com.uca.tfg.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.dao.OrderLineDAO;
import com.uca.tfg.model.OrderLine;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderLineServiceTest {
	
	@MockBean
	private OrderLineDAO dao;
	
	@Autowired
	private OrderLineManager service;
	
	@Test
	public void testGetAllOrderLines() {
		given(dao.findAll()).willReturn(Arrays.asList(new OrderLine()));
		assertNotNull(service.getAllOrderLines());
	}

	@Test
	public void testGetOrderLineById() {
		given(dao.findById(anyLong())).willReturn(Optional.of(new OrderLine()));
		assertNotNull(service.getOrderLine(anyLong()));
	}

	@Test
	public void testOrderLineNotFound() {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNull(service.getOrderLine(anyLong()));
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

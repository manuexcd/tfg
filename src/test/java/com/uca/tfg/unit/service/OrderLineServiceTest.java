package com.uca.tfg.unit.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.uca.tfg.exception.OrderLineNotFoundException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.repository.OrderLineRepository;
import com.uca.tfg.service.OrderLineServiceImpl;
import com.uca.tfg.service.OrderServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OrderLineServiceTest {

	@Mock
	private OrderLineRepository dao;

	@Mock
	private OrderServiceImpl orderService;

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
	public void testDeleteOrderLine() throws OrderLineNotFoundException, OrderNotFoundException {
		Order order = new Order();
		order.setId(1);
		OrderLine orderLine = new OrderLine();
		orderLine.setOrder(order);
		List<OrderLine> list = new ArrayList<>();
		list.add(orderLine);
		order.setOrderLines(list);
		dao.save(orderLine);
		long id = orderLine.getId();
		given(dao.existsById(id)).willReturn(true);
		given(dao.getOne(id)).willReturn(orderLine);
		given(orderService.getOrder(anyLong())).willReturn(order);
		service.deleteOrderLine(id);
		given(dao.existsById(id)).willReturn(false);
		assertFalse(dao.existsById(id));
	}

	@Test(expected = OrderLineNotFoundException.class)
	public void testDeleteOrderLineNotFound() throws OrderLineNotFoundException, OrderNotFoundException {
		given(dao.existsById(anyLong())).willReturn(false);
		service.deleteOrderLine((long) 1);
	}
}

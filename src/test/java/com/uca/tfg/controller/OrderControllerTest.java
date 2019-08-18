package com.uca.tfg.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uca.tfg.dto.OrderLineDTO;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.mapper.OrderLineMapper;
import com.uca.tfg.mapper.OrderMapper;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	private MockMvc mvc;

	@Mock
	private OrderService service;

	@Mock
	private OrderMapper mapper;

	@Mock
	private OrderLineMapper orderLineMapper;

	@InjectMocks
	private OrderController controller;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetAllOrders() throws Exception {
		mvc.perform(get("/orders").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetAllOrdersByOrderStatus() throws Exception {
		mvc.perform(get("/orders/status").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

//	@Test
//	public void testGetAllOrdersByUser() throws Exception {
//		Order order = new Order();
//		List<Order> orders = new ArrayList<>();
//		orders.add(order);
//		given(service.getOrdersByUser(anyLong(), eq(pageRequest))).willReturn(new PageImpl<>(orders));
//		mvc.perform(get("/orders/user/2").contentType(APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(content().string(containsString("orderStatus")));
//	}

	@Test
	public void testGetAllOrdersByUserNotFound() throws Exception {
		mvc.perform(get("/orders/user/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testGetOrderLines() throws Exception {
		OrderLine orderLine = new OrderLine();
		OrderLineDTO orderLineDto = new OrderLineDTO();
		given(service.getOrderLines(anyLong())).willReturn(Arrays.asList(orderLine));
		given(orderLineMapper.mapEntityListToDtoList(any())).willReturn(Arrays.asList(orderLineDto));
		mvc.perform(get("/orders/1/lines").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetOrderLinesNotFound() throws Exception {
		given(service.getOrderLines(anyLong())).willThrow(new OrderNotFoundException());
		mvc.perform(get("/orders/1/lines").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testGetOrder() throws Exception {
		Order order = new Order();
		given(service.getOrder(anyLong())).willReturn(order);
		mvc.perform(get("/orders/1").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetOrderNotFound() throws Exception {
		given(service.getOrder(anyLong())).willThrow(new OrderNotFoundException());
		mvc.perform(get("/orders/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteOrder() throws Exception {
		Order order = new Order();
		given(service.getOrder(anyLong())).willReturn(order);
		mvc.perform(delete("/orders/1").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteOrderNotFound() throws Exception {
		Mockito.doThrow(new OrderNotFoundException()).when(service).getOrder(anyLong());
		mvc.perform(delete("/orders/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}

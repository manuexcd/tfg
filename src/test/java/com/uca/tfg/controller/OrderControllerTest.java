package com.uca.tfg.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.service.OrderManager;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	private MockMvc mvc;

	@Mock
	private OrderManager service;

	@InjectMocks
	private OrderController controller;
	
	private Pageable pageRequest = PageRequest.of(0, 10);

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
		given(service.getOrderLines(anyLong())).willReturn(Arrays.asList(orderLine));
		mvc.perform(get("/orders/1/lines").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("quantity")));
	}

	@Test
	public void testGetOrderLinesNotFound() throws Exception {
		given(service.getOrderLines(anyLong())).willReturn(null);
		mvc.perform(get("/orders/1/lines").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testGetOrder() throws Exception {
		Order order = new Order();
		given(service.getOrder(anyLong())).willReturn(order);
		mvc.perform(get("/orders/1").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("orderStatus")));
	}

	@Test
	public void testGetOrderNotFound() throws Exception {
		given(service.getOrder(anyLong())).willReturn(null);
		mvc.perform(get("/orders/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testAddOrderLine() throws Exception {
		OrderLine orderLine = new OrderLine();
		String body = "{\"quantity\":\"1\"}";
		given(service.addOrderLine(anyLong(), anyLong(), anyInt())).willReturn(orderLine);
		mvc.perform(post("/orders/1/1-1").content(body).contentType(APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andExpect(content().string(containsString("quantity")));
	}

	@Test
	public void testDeleteOrder() throws Exception {
		Order order = new Order();
		given(service.getOrder(anyLong())).willReturn(order);
		mvc.perform(delete("/orders/1").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("orderStatus")));
	}

	@Test
	public void testDeleteOrderNotFound() throws Exception {
		given(service.getOrder(anyLong())).willReturn(null);
		mvc.perform(delete("/orders/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}

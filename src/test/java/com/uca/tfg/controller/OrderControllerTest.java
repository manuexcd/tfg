package com.uca.tfg.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.service.OrderManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

	private MockMvc mvc;

	@MockBean
	private OrderManager service;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetAllOrders() throws Exception {
		Order order = new Order();
		given(service.getAllOrdersByDate()).willReturn(Arrays.asList(order));
		mvc.perform(get("/orders").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("orderStatus")));
	}

	@Test
	public void testGetAllOrdersByOrderStatus() throws Exception {
		Order order = new Order();
		given(service.getAllOrdersByOrderStatus()).willReturn(Arrays.asList(order));
		mvc.perform(get("/orders/status").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("orderStatus")));
	}

	@Test
	public void testGetAllOrdersByUser() throws Exception {
		Order order = new Order();
		given(service.getOrdersByUser(anyLong())).willReturn(Arrays.asList(order));
		mvc.perform(get("/orders/user/1").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("orderStatus")));
	}

	@Test
	public void testGetAllOrdersByUserNotFound() throws Exception {
		given(service.getOrdersByUser(anyLong())).willReturn(null);
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

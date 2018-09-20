package com.uca.tfg.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.uca.tfg.model.Product;
import com.uca.tfg.service.OrderLineManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderLineControllerTest {

	private MockMvc mvc;

	@MockBean
	private OrderLineManager service;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testGetAllOrderLines() throws Exception {
		OrderLine orderLine = new OrderLine();
		given(service.getAllOrderLines()).willReturn(Arrays.asList(orderLine));
		mvc.perform(get("/orderLines").contentType(APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(containsString("id")));
	}
	
	@Test
	public void testGetOrderLine() throws Exception {
		OrderLine orderLine = new OrderLine();
		orderLine.setProduct(new Product());
		orderLine.setOrder(new Order());
		orderLine.setQuantity(1);
		given(service.getOrderLine(anyLong())).willReturn(orderLine);
		mvc.perform(get("/orderLines/1").contentType(APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(containsString("quantity")));
	}
	
	@Test
	public void testGetOrderLineNotFound() throws Exception {
		given(service.getOrderLine(anyLong())).willReturn(null);
		mvc.perform(get("/orderLines/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteOrderLine() throws Exception {
		OrderLine orderLine = new OrderLine();
		orderLine.setProduct(new Product());
		orderLine.setOrder(new Order());
		orderLine.setQuantity(1);
		given(service.getOrderLine(anyLong())).willReturn(orderLine);
		mvc.perform(delete("/orderLines/1").contentType(APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(containsString("quantity")));
	}
	
	@Test
	public void testDeleteOrderLineNotFound() throws Exception {
		given(service.getOrderLine(anyLong())).willReturn(null);
		mvc.perform(delete("/orderLines/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}

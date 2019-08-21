package com.uca.tfg.unit.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uca.tfg.controller.OrderLineController;
import com.uca.tfg.exception.OrderLineNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.Product;
import com.uca.tfg.service.OrderLineService;

@RunWith(MockitoJUnitRunner.class)
public class OrderLineControllerTest {

	private MockMvc mvc;

	@Mock
	private OrderLineService service;
	
	@InjectMocks
	private OrderLineController controller;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
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
		given(service.getOrderLine(anyLong())).willThrow(new OrderLineNotFoundException());
		mvc.perform(get("/orderLines/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteOrderLine() throws Exception {
		mvc.perform(delete("/orderLines/1").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteOrderLineNotFound() throws Exception {
		Mockito.doThrow(new OrderLineNotFoundException()).when(service).deleteOrderLine(anyLong());
		mvc.perform(delete("/orderLines/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}

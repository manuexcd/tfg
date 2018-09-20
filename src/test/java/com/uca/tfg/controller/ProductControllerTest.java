package com.uca.tfg.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.uca.tfg.model.Product;
import com.uca.tfg.service.ProductManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

	private MockMvc mvc;

	@MockBean
	private ProductManager service;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetAllProducts() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		given(service.getAllProducts()).willReturn(Arrays.asList(product));
		mvc.perform(get("/products").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("prueba")));
	}

	@Test
	public void testGetAllProductsOrderByName() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		given(service.getAllProductsOrderByName()).willReturn(Arrays.asList(product));
		mvc.perform(get("/products/name").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("prueba")));
	}

	@Test
	public void testGetAllProductsOrderByPrice() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		given(service.getAllProductsOrderByPrice()).willReturn(Arrays.asList(product));
		mvc.perform(get("/products/price").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("prueba")));
	}

	@Test
	public void testGetAllProductsOrderByPriceDesc() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		given(service.getAllProductsOrderByPriceDesc()).willReturn(Arrays.asList(product));
		mvc.perform(get("/products/pricedesc").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("prueba")));
	}

	@Test
	public void testGetAllProductsOrderByStock() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		given(service.getAllProductsOrderByStockAvailable()).willReturn(Arrays.asList(product));
		mvc.perform(get("/products/stock").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("prueba")));
	}

	@Test
	public void testGetAllProductsByParam() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		given(service.getProductsByParam(anyString())).willReturn(Arrays.asList(product));
		mvc.perform(get("/products/search/a").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("prueba")));
	}

	@Test
	public void testGetProductById() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		given(service.getProduct(anyLong())).willReturn(product);
		mvc.perform(get("/products/1").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("prueba")));
	}

	@Test
	public void testGetProductByIdNotFound() throws Exception {
		given(service.getProduct(anyLong())).willReturn(null);
		mvc.perform(get("/products/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateProduct() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		String body = "{\n	\"name\":\"prueba\",\n	\"description\":\"prueba\"\n}";
		given(service.addProduct(any())).willReturn(product);
		mvc.perform(put("/products/1").content(body).contentType(APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andExpect(content().string(containsString("prueba")));
	}

	@Test
	public void testAddProduct() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		String body = "{\n	\"name\":\"prueba\",\n	\"description\":\"prueba\"\n}";
		given(service.addProduct(any())).willReturn(product);
		mvc.perform(post("/products").content(body).contentType(APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("prueba")));
	}

	@Test
	public void testDeleteProductById() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		given(service.getProduct(anyLong())).willReturn(product);
		mvc.perform(delete("/products/1").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("prueba")));
	}

	@Test
	public void testDeleteProductByIdNotFound() throws Exception {
		given(service.getProduct(anyLong())).willReturn(null);
		mvc.perform(delete("/products/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}

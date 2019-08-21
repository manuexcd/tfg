package com.uca.tfg.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uca.tfg.controller.ProductController;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.mapper.ProductMapper;
import com.uca.tfg.model.Product;
import com.uca.tfg.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	private MockMvc mvc;

	@Mock
	private ProductService service;
	
	@Mock
	private ProductMapper mapper;
	
	@InjectMocks
	private ProductController controller;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetAllProducts() throws Exception {
		mvc.perform(get("/products").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetAllProductsOrderByName() throws Exception {
		mvc.perform(get("/products/name").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetAllProductsOrderByPrice() throws Exception {
		mvc.perform(get("/products/price").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetAllProductsOrderByPriceDesc() throws Exception {
		mvc.perform(get("/products/pricedesc").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetAllProductsOrderByStock() throws Exception {
		mvc.perform(get("/products/stock").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetAllProductsByParam() throws Exception {
		mvc.perform(get("/products/search/a").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetProductById() throws Exception {
		Product product = new Product();
		product.setName("prueba");
		product.setDescription("prueba");
		product.setPrice(10);
		product.setStockAvailable(100);
		given(service.getProduct(anyLong())).willReturn(product);
		mvc.perform(get("/products/1").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetProductByIdNotFound() throws Exception {
		given(service.getProduct(anyLong())).willThrow(new ProductNotFoundException());
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
		given(mapper.mapDtoToEntity(any())).willReturn(product);
		mvc.perform(put("/products/1").content(body).contentType(APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
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
		given(mapper.mapDtoToEntity(any())).willReturn(product);
		mvc.perform(post("/products").content(body).contentType(APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testDeleteProductById() throws Exception {
		mvc.perform(delete("/products/1").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}
}

package com.uca.tfg.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.dao.ProductDAO;
import com.uca.tfg.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
	
	@MockBean
	private ProductDAO dao;
	
	@Autowired
	private ProductManager service;
	
	@Test
	public void testGetAllProducts() {
		given(dao.findAll()).willReturn(Arrays.asList(new Product()));
		assertNotNull(service.getAllProducts());
	}
	
	@Test
	public void testGetAllProductsOrderByName() {
		given(dao.findAllByOrderByName()).willReturn(Arrays.asList(new Product()));
		assertNotNull(service.getAllProductsOrderByName());
	}
	
	@Test
	public void testGetAllProductsOrderByPrice() {
		given(dao.findAllByOrderByPrice()).willReturn(Arrays.asList(new Product()));
		assertNotNull(service.getAllProductsOrderByPrice());
	}
	
	@Test
	public void testGetAllProductsOrderByPriceDesc() {
		given(dao.findAllByOrderByPriceDesc()).willReturn(Arrays.asList(new Product()));
		assertNotNull(service.getAllProductsOrderByPriceDesc());
	}
	
	@Test
	public void testGetAllProductsOrderByStockAvailable() {
		given(dao.findAllByOrderByStockAvailable()).willReturn(Arrays.asList(new Product()));
		assertNotNull(service.getAllProductsOrderByStockAvailable());
	}
	
	@Test 
	public void testGetProductsByParam() {
		given(dao.findByParam(anyString())).willReturn(Arrays.asList(new Product()));
		assertNotNull(service.getProductsByParam(anyString()));
	}
	
	@Test
	public void testGetProductById() {
		given(dao.findById(anyLong())).willReturn(Optional.of(new Product()));
		assertNotNull(service.getProduct(anyLong()));
	}
	
	@Test
	public void testProductNotFound() {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNull(service.getProduct(anyLong()));
	}
	
	@Test
	public void testAddProduct() {
		Product product = new Product();
		given(dao.save(product)).willReturn(product);
		assertNotNull(service.addProduct(product));
	}
	
	@Test
	public void testDeleteProduct() {
		Product product = new Product();
		dao.save(product);
		long id = product.getId();
		service.deleteProduct(id);
		assertFalse(dao.existsById(id));
	}
}
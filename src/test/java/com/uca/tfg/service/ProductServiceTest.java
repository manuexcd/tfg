package com.uca.tfg.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.model.Product;
import com.uca.tfg.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
	
	@MockBean
	private ProductRepository dao;
	
	@Autowired
	private ProductManager service;
	
	private Pageable pageRequest;
	
	@Test
	public void testGetAllProducts() {
		given(dao.findAll(pageRequest)).willReturn(Page.empty());
		assertNotNull(service.getAllProducts(pageRequest));
	}
	
	@Test
	public void testGetAllProductsOrderByName() {
		given(dao.findAllByOrderByName(pageRequest)).willReturn(Page.empty());
		assertNotNull(service.getAllProductsOrderByName(pageRequest));
	}
	
	@Test
	public void testGetAllProductsOrderByPrice() {
		given(dao.findAllByOrderByPrice(pageRequest)).willReturn(Page.empty());
		assertNotNull(service.getAllProductsOrderByPrice(pageRequest));
	}
	
	@Test
	public void testGetAllProductsOrderByPriceDesc() {
		given(dao.findAllByOrderByPriceDesc(pageRequest)).willReturn(Page.empty());
		assertNotNull(service.getAllProductsOrderByPriceDesc(pageRequest));
	}
	
	@Test
	public void testGetAllProductsOrderByStockAvailable() {
		given(dao.findAllByOrderByStockAvailable(pageRequest)).willReturn(Page.empty());
		assertNotNull(service.getAllProductsOrderByStockAvailable(pageRequest));
	}
	
	@Test 
	public void testGetProductsByParam() {
		given(dao.findByParam(anyString(), eq(pageRequest))).willReturn(Page.empty());
		assertNotNull(service.getProductsByParam(anyString(), eq(pageRequest)));
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
package com.uca.tfg.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.uca.tfg.dao.ProductDAO;
import com.uca.tfg.model.Product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class TestProductService {
	
	@Mock
	private ProductDAO products;

	@InjectMocks
	private ProductManager service = new ProductManagerImp();
	
	@Test
	public void testGetAllProducts() {
		when(service.getAllProducts()).thenReturn(new ArrayList<Product>());
		Collection<Product> products = service.getAllProducts();
		Assert.assertNotNull(products);
	}
	
	@Test
	public void testGetProductById() {
		when(service.getProduct(1)).thenReturn(new Product());
		assertNotNull(service.getProduct(1));
	}
	
	@Test
	public void testProductNotFound() {
		when(service.getProduct(0)).thenReturn(null);
		assertNull(service.getProduct(0));
	}
	
	@Test
	public void testGetProductName() {
		Product product = mock(Product.class);
		when(product.getName()).thenReturn("Name");
		Assert.assertEquals("Name", product.getName());
	}
}
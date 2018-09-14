package com.uca.tfg.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.model.Product;

@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductServiceTest {
	
	@MockBean
	private ProductManager service;
	
	@Test
	public void testGetAllProducts() {
		given(service.getAllProducts()).willReturn(Arrays.asList(new Product()));
		assertNotNull(service.getAllProducts());
	}
	
	@Test
	public void testGetProductById() {
		given(service.getProduct(Mockito.anyLong())).willReturn(Optional.of(new Product()).get());
		assertNotNull(service.getProduct(Mockito.anyLong()));
	}
	
	@Test
	public void testProductNotFound() {
		given(service.getProduct(Mockito.anyLong())).willReturn(null);
		assertNull(service.getProduct(Mockito.anyLong()));
	}
	
	/*@Test
	public void testGetProductName() {
		Product product = mock(Product.class);
		when(product.getName()).thenReturn("Name");
		Assert.assertEquals("Name", product.getName());
	}*/
}
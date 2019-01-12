package com.uca.tfg.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {

	@Test
	public void updateMoreStock() {
		Product product = new Product();
		product.setStockAvailable(0);
		product.updateStock(10);
		assertTrue(product.getStockAvailable() == 10);
	}
	
	@Test
	public void updateLessStock() {
		Product product = new Product();
		product.setStockAvailable(10);
		product.updateStock(-10);
		assertTrue(product.getStockAvailable() == 0);
	}
}

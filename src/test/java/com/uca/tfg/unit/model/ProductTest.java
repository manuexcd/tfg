package com.uca.tfg.unit.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.uca.tfg.model.Image;
import com.uca.tfg.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {
	
	@Test
	public void testConstructor() {
		Product product = new Product("nombre", "descripcion", 100, 100, true, new Image());
		assertNotNull(product);
	}

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

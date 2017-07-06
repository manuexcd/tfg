package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.model.Product;

public interface ProductManager {
	public Collection<Product> getAllProducts();

	public ResponseEntity<Product> getProduct(long id);

	public Product addProduct(Product product);

	public ResponseEntity<Product> deleteProduct(long id);
}

package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.model.Product;

public interface ProductManager {
	public Collection<Product> getAllProducts();

	public Product getProduct(long id);
	
	public Product updateProduct(Product product);

	public Product addProduct(Product product);

	public Product deleteProduct(long id);
}

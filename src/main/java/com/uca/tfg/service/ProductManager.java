package com.uca.tfg.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uca.tfg.model.Product;

public interface ProductManager {
	public Page<Product> getAllProducts(Pageable page);
	
	public Page<Product> getAllProductsOrderByName(Pageable page);
	
	public Page<Product> getAllProductsOrderByPrice(Pageable page);
	
	public Page<Product> getAllProductsOrderByPriceDesc(Pageable page);
	
	public Page<Product> getAllProductsOrderByStockAvailable(Pageable page);
	
	public Page<Product> getProductsByParam(String param, Pageable page);

	public Product getProduct(long id);
	
	public Product addProduct(Product product);

	public void deleteProduct(long id);
}

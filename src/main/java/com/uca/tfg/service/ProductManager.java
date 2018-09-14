package com.uca.tfg.service;

import java.util.Collection;

import com.uca.tfg.model.Product;

public interface ProductManager {
	public Collection<Product> getAllProducts();
	
	public Collection<Product> getAllProductsOrderByName();
	
	public Collection<Product> getAllProductsOrderByPrice();
	
	public Collection<Product> getAllProductsOrderByPriceDesc();
	
	public Collection<Product> getAllProductsOrderByStockAvailable();
	
	public Collection<Product> getProductsByParam(String param);

	public Product getProduct(long id);
	
	public Product addProduct(Product product);

	public Product deleteProduct(long id);
}

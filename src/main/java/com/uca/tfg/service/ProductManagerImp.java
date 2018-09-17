package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.ProductDAO;
import com.uca.tfg.model.Product;

@Service("productManager")
@DependsOn("imageManager")
public class ProductManagerImp implements ProductManager {

	@Autowired
	private ProductDAO products;

	@Override
	public Collection<Product> getAllProducts() {
		return products.findAll();
	}

	@Override
	public Collection<Product> getAllProductsOrderByName() {
		return products.findAllByOrderByName();
	}

	@Override
	public Collection<Product> getAllProductsOrderByPrice() {
		return products.findAllByOrderByPrice();
	}

	@Override
	public Collection<Product> getAllProductsOrderByPriceDesc() {
		return products.findAllByOrderByPriceDesc();
	}

	@Override
	public Collection<Product> getAllProductsOrderByStockAvailable() {
		return products.findAllByOrderByStockAvailable();
	}

	@Override
	public Collection<Product> getProductsByParam(String param) {
		return products.findByParam(param);
	}

	@Override
	public Product getProduct(long id) {
		return products.findById(id).orElse(null);
	}

	@Override
	public Product addProduct(Product product) {
		products.save(product);

		return product;
	}

	@Override
	public void deleteProduct(long id) {
		products.deleteById(id);
	}
}

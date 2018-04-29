package com.uca.tfg.service;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.ProductDAO;
import com.uca.tfg.model.Product;

@Service("productManager")
public class ProductManagerImp implements ProductManager {

	@Autowired
	private ProductDAO products;

	@PostConstruct
	public void init() {
		if (products.findAll().isEmpty()) {
			products.save(new Product("ProductOne", "Product one description", 12.50, 20, null));
			products.save(new Product("ProductTwo", "Product two description", 21.95, 56, null));
			products.save(new Product("ProductThree", "Product three description", 9.95, 123, null));
			products.save(new Product("ProductFour", "Product four description", 45.50, 200, null));
			products.save(new Product("ProductFive", "Product five description", 20, 10, null));
		}
	}

	@Override
	public Collection<Product> getAllProducts() {
		return products.findAll();
	}

	@Override
	public Product getProduct(long id) {
		return products.findOne(id);
	}
	
	@Override
	public Product updateProduct(Product product) {
		products.save(product);
		
		return product;
	}

	@Override
	public Product addProduct(Product product) {
		products.save(product);

		return product;
	}

	@Override
	public Product deleteProduct(long id) {
		Product product = products.findOne(id);

		if (product != null) {
			products.delete(id);
			return product;
		} else
			return null;
	}
}

package com.uca.tfg.service;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.Product;
import com.uca.tfg.dao.ProductDAO;

@Service("productManager")
public class ProductManagerImp implements ProductManager {

	@Autowired
	private ProductDAO products;

	@PostConstruct
	public void init() {
		if (products.findAll().isEmpty()) {
			products.save(new Product("ProductOne", "Product one description", 12.50, 20));
			products.save(new Product("ProductTwo", "Product two description", 21.95, 56));
			products.save(new Product("ProductThree", "Product three description", 9.95, 123));
			products.save(new Product("ProductFour", "Product four description", 45.50, 200));
			products.save(new Product("ProductFive", "Product five description", 20, 10));
		}
	}

	public Collection<Product> getAllProducts() {
		return products.findAll();
	}

	public ResponseEntity<Product> getProduct(long id) {
		Product product = products.findOne(id);

		if (product != null)
			return new ResponseEntity<>(product, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public Product addProduct(Product product) {
		products.save(product);

		return product;
	}

	public ResponseEntity<Product> deleteProduct(long id) {
		Product product = products.findOne(id);

		if (product != null) {
			products.delete(id);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

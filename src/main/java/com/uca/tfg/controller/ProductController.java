package com.uca.tfg.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.model.Product;
import com.uca.tfg.service.ProductManager;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductManager productManager;

	@GetMapping
	public ResponseEntity<Collection<Product>> getAllProducts() {
		return new ResponseEntity<>(productManager.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping(value = "/name")
	public ResponseEntity<Collection<Product>> getAllProductsOrderByName() {
		return new ResponseEntity<>(productManager.getAllProductsOrderByName(), HttpStatus.OK);
	}

	@GetMapping(value = "/price")
	public ResponseEntity<Collection<Product>> getAllProductsOrderByPrice() {
		return new ResponseEntity<>(productManager.getAllProductsOrderByPrice(), HttpStatus.OK);
	}

	@GetMapping(value = "/pricedesc")
	public ResponseEntity<Collection<Product>> getAllProductsOrderByPriceDesc() {
		return new ResponseEntity<>(productManager.getAllProductsOrderByPriceDesc(), HttpStatus.OK);
	}

	@GetMapping(value = "/stock")
	public ResponseEntity<Collection<Product>> getAllProductsOrderByStockAvailable() {
		return new ResponseEntity<>(productManager.getAllProductsOrderByStockAvailable(), HttpStatus.OK);
	}

	@GetMapping(value = "/search/{param}")
	public ResponseEntity<Collection<Product>> getProductsByParam(@PathVariable String param) {
		return new ResponseEntity<>(productManager.getProductsByParam(param), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable long id) {
		Product product = productManager.getProduct(id);
		if (product != null)
			return new ResponseEntity<>(product, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productManager.addProduct(product), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productManager.addProduct(product), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
		Product product = productManager.getProduct(id);
		if (product != null) {
			productManager.deleteProduct(id);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

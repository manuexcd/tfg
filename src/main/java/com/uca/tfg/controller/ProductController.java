package com.uca.tfg.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.model.Product;
import com.uca.tfg.service.ProductManager;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductManager productManager;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Product> getAllProducts() {
		return productManager.getAllProducts();
	}
	
	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public Collection<Product> getAllProductsOrderByName() {
		return productManager.getAllProductsOrderByName();
	}
	
	@RequestMapping(value = "/price", method = RequestMethod.GET)
	public Collection<Product> getAllProductsOrderByPrice() {
		return productManager.getAllProductsOrderByPrice();
	}
	
	@RequestMapping(value = "/pricedesc", method = RequestMethod.GET)
	public Collection<Product> getAllProductsOrderByPriceDesc() {
		return productManager.getAllProductsOrderByPriceDesc();
	}
	
	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public Collection<Product> getAllProductsOrderByStockAvailable() {
		return productManager.getAllProductsOrderByStockAvailable();
	}
	
	@RequestMapping(value = "/search/{param}", method = RequestMethod.GET)
	public Collection<Product> getProductsByParam(@PathVariable String param) {
		return productManager.getProductsByParam(param);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable long id) {
		Product product = productManager.getProduct(id);
		if (product != null)
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Product updateProduct(@RequestBody Product product) {
		return productManager.updateProduct(product);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Product addProduct(@RequestBody Product product) {
		return productManager.addProduct(product);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
		Product product = productManager.getProduct(id);
		if (product != null) {
			productManager.deleteProduct(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

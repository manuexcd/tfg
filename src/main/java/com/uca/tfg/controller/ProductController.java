package com.uca.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.model.Constants;
import com.uca.tfg.model.Product;
import com.uca.tfg.service.ProductManager;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductManager productManager;

	@GetMapping
	public ResponseEntity<Page<Product>> getAllProducts(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(productManager.getAllProducts(PageRequest.of(page, pageSize)), HttpStatus.OK);
	}

	@GetMapping(value = "/name")
	public ResponseEntity<Page<Product>> getAllProductsOrderByName(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(productManager.getAllProductsOrderByName(PageRequest.of(page, pageSize)),
				HttpStatus.OK);
	}

	@GetMapping(value = "/price")
	public ResponseEntity<Page<Product>> getAllProductsOrderByPrice(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(productManager.getAllProductsOrderByPrice(PageRequest.of(page, pageSize)),
				HttpStatus.OK);
	}

	@GetMapping(value = "/pricedesc")
	public ResponseEntity<Page<Product>> getAllProductsOrderByPriceDesc(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(productManager.getAllProductsOrderByPriceDesc(PageRequest.of(page, pageSize)),
				HttpStatus.OK);
	}

	@GetMapping(value = "/stock")
	public ResponseEntity<Page<Product>> getAllProductsOrderByStockAvailable(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(productManager.getAllProductsOrderByStockAvailable(PageRequest.of(page, pageSize)),
				HttpStatus.OK);
	}

	@GetMapping(value = "/search/{param}")
	public ResponseEntity<Page<Product>> getProductsByParam(@PathVariable String param,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(productManager.getProductsByParam(param, PageRequest.of(page, pageSize)),
				HttpStatus.OK);
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

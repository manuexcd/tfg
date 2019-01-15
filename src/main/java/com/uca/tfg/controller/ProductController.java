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

import com.uca.tfg.dto.ProductDTO;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.mapper.ProductMapper;
import com.uca.tfg.model.Constants;
import com.uca.tfg.model.Product;
import com.uca.tfg.service.ProductManager;

@RestController
@RequestMapping(value = Constants.PATH_PRODUCTS)
public class ProductController {

	@Autowired
	private ProductManager productManager;

	@Autowired
	private ProductMapper mapper;

	@GetMapping
	public ResponseEntity<Page<ProductDTO>> getAllProducts(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(
				mapper.mapEntityPageToDtoPage(productManager.getAllProducts(PageRequest.of(page, pageSize))),
				HttpStatus.OK);
	}

	@GetMapping(value = "/name")
	public ResponseEntity<Page<ProductDTO>> getAllProductsOrderByName(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(
				mapper.mapEntityPageToDtoPage(productManager.getAllProductsOrderByName(PageRequest.of(page, pageSize))),
				HttpStatus.OK);
	}

	@GetMapping(value = "/price")
	public ResponseEntity<Page<ProductDTO>> getAllProductsOrderByPrice(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(mapper.mapEntityPageToDtoPage(
				productManager.getAllProductsOrderByPrice(PageRequest.of(page, pageSize))), HttpStatus.OK);
	}

	@GetMapping(value = "/pricedesc")
	public ResponseEntity<Page<ProductDTO>> getAllProductsOrderByPriceDesc(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(mapper.mapEntityPageToDtoPage(
				productManager.getAllProductsOrderByPriceDesc(PageRequest.of(page, pageSize))), HttpStatus.OK);
	}

	@GetMapping(value = "/stock")
	public ResponseEntity<Page<ProductDTO>> getAllProductsOrderByStockAvailable(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(
				mapper.mapEntityPageToDtoPage(
						productManager.getAllProductsOrderByStockAvailable(PageRequest.of(page, pageSize))),
				HttpStatus.OK);
	}

	@GetMapping(value = "/search/{param}")
	public ResponseEntity<Page<ProductDTO>> getProductsByParam(@PathVariable String param,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(
				mapper.mapEntityPageToDtoPage(productManager.getProductsByParam(param, PageRequest.of(page, pageSize))),
				HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable long id) throws ProductNotFoundException {
		Product product = productManager.getProduct(id);
		if (product != null)
			return new ResponseEntity<>(mapper.mapEntitytoDto(product), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO dto) {
		return new ResponseEntity<>(mapper.mapEntitytoDto(productManager.addProduct(mapper.mapDtoToEntity(dto))),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO dto) {
		return new ResponseEntity<>(mapper.mapEntitytoDto(productManager.addProduct(mapper.mapDtoToEntity(dto))),
				HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> deleteProduct(@PathVariable long id) throws ProductNotFoundException {
		Product product = productManager.getProduct(id);
		if (product != null) {
			productManager.deleteProduct(id);
			return new ResponseEntity<>(mapper.mapEntitytoDto(product), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

package com.uca.tfg.service;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.ImageDAO;
import com.uca.tfg.dao.ProductDAO;
import com.uca.tfg.model.Product;

@Service("productManager")
@DependsOn("imageManager")
public class ProductManagerImp implements ProductManager {

	@Autowired
	private ProductDAO products;
	
	@Autowired
	private ImageDAO images;

	@PostConstruct
	public void init() {
		if (products.findAll().isEmpty()) {
			products.save(new Product("PlayStation 4", "PlayStation 4 Slim representa una consola con 500 GB. Es un regalo adecuado en cualquier momento.", 299.99, 20, images.findById((long) 1).get()));
			products.save(new Product("Kindle Paperwhite", "El e-reader Kindle Paperwhite está especialmente diseñado para leer. Disfruta de tu pasión por la lectura sin molestas interrupciones como alertas de e-mail y notificaciones.", 21.95, 56, images.findById((long) 2).get()));
			products.save(new Product("Bosch Tassimo Suny", "Cafetera multibebidas automática de cápsulas con sistema SmartStart, color azul pacífico", 39.00, 123, images.findById((long) 3).get()));
			products.save(new Product("ProductFour", "Product four description", 45.50, 200, images.findById((long) 4).get()));
			products.save(new Product("ProductFive", "Product five description", 20, 10, images.findById((long) 5).get()));
		}
	}

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
		return products.findById(id).get();
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
		Product product = products.findById(id).get();

		if (product != null) {
			products.delete(product);
			return product;
		} else
			return null;
	}
}

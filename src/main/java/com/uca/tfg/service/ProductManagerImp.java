package com.uca.tfg.service;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.uca.tfg.model.Image;
import com.uca.tfg.model.Product;
import com.uca.tfg.repository.ImageRepository;
import com.uca.tfg.repository.ProductRepository;

@Service("productManager")
@DependsOn("imageManager")
public class ProductManagerImp implements ProductManager {

	@Autowired
	private ProductRepository products;

	@Autowired
	private ImageRepository images;

	@PostConstruct
	public void init() {
		if (products.findAll().isEmpty()) {
			Optional<Image> image3 = images.findById((long) 3);
			Optional<Image> image4 = images.findById((long) 4);
			Optional<Image> image5 = images.findById((long) 5);
			if (image3.isPresent())
				products.save(new Product("PlayStation 4", "PlayStation 4", 300, 100, true, image3.get()));
			if (image4.isPresent())
				products.save(new Product("Xbox", "Xbox", 200, 100, true, image4.get()));
			if (image5.isPresent())
				products.save(new Product("Nintendo Switch", "Nintendo Switch", 300, 100, true, image5.get()));
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

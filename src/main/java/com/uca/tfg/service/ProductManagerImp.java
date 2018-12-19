package com.uca.tfg.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Product> getAllProducts(Pageable page) {
		return products.findAll(page);
	}

	@Override
	public Page<Product> getAllProductsOrderByName(Pageable page) {
		return products.findAllByOrderByName(page);
	}

	@Override
	public Page<Product> getAllProductsOrderByPrice(Pageable page) {
		return products.findAllByOrderByPrice(page);
	}

	@Override
	public Page<Product> getAllProductsOrderByPriceDesc(Pageable page) {
		return products.findAllByOrderByPriceDesc(page);
	}

	@Override
	public Page<Product> getAllProductsOrderByStockAvailable(Pageable page) {
		return products.findAllByOrderByStockAvailable(page);
	}

	@Override
	public Page<Product> getProductsByParam(String param, Pageable page) {
		return products.findByParam(param, page);
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

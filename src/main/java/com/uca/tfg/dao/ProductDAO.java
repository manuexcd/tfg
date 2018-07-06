package com.uca.tfg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uca.tfg.model.Product;

@Repository("ProductDAO")
public interface ProductDAO extends JpaRepository<Product, Long>{

	List<Product> findAllByOrderByName();
	
	List<Product> findAllByOrderByPrice();
	
	List<Product> findAllByOrderByPriceDesc();
	
	List<Product> findAllByOrderByStockAvailable();
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1% OR p.description LIKE %?1%")
	List<Product> findByParam(String param);
}

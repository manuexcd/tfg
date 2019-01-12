package com.uca.tfg.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uca.tfg.model.Product;

@Repository("ProductDAO")
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByName(String name);

	Page<Product> findAllByOrderByName(Pageable page);
	
	Page<Product> findAllByOrderByPrice(Pageable page);
	
	Page<Product> findAllByOrderByPriceDesc(Pageable page);
	
	Page<Product> findAllByOrderByStockAvailable(Pageable page);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1% OR p.description LIKE %?1%")
	Page<Product> findByParam(String param, Pageable page);
}

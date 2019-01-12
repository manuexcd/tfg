package com.uca.tfg.mapper;

import org.springframework.stereotype.Component;

import com.uca.tfg.dto.ProductDTO;
import com.uca.tfg.model.Product;

@Component
public class ProductMapperImpl extends GenericMapperImpl<Product, ProductDTO> implements ProductMapper {

	@Override
	public Class<Product> getClazz() {
		return Product.class;
	}

	@Override
	public Class<ProductDTO> getDtoClazz() {
		return ProductDTO.class;
	}
}

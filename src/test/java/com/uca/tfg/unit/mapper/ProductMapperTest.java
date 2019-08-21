package com.uca.tfg.unit.mapper;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;

import com.uca.tfg.dto.ProductDTO;
import com.uca.tfg.mapper.ProductMapperImpl;
import com.uca.tfg.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductMapperTest {

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private ProductMapperImpl mapper;

	@Test
	public void testMapDtoToEntity() {
		ProductDTO dto = new ProductDTO();
		given(modelMapper.map(dto, Product.class)).willReturn(new Product());
		assertNotNull(mapper.mapDtoToEntity(dto));
	}

	@Test
	public void testMapEntityToDto() {
		Product product = new Product();
		given(modelMapper.map(product, ProductDTO.class)).willReturn(new ProductDTO());
		assertNotNull(mapper.mapEntityToDto(product));
	}

	@Test
	public void testMapDtoPageToEntityPage() {
		List<ProductDTO> listDto = new ArrayList<ProductDTO>();
		listDto.add(new ProductDTO());
		assertNotNull(mapper.mapDtoPageToEntityPage(new PageImpl<ProductDTO>(listDto)));
	}

	@Test
	public void testMapEntityPageToDtoPage() {
		List<Product> list = new ArrayList<Product>();
		list.add(new Product());
		assertNotNull(mapper.mapEntityPageToDtoPage(new PageImpl<Product>(list)));
	}

	@Test
	public void testMapDtoListToEntityList() {
		List<ProductDTO> listDto = new ArrayList<ProductDTO>();
		listDto.add(new ProductDTO());
		assertNotNull(mapper.mapDtoListToEntityList(listDto));
	}

	@Test
	public void testMapEntityListToDtoList() {
		List<Product> list = new ArrayList<Product>();
		list.add(new Product());
		assertNotNull(mapper.mapEntityListToDtoList(list));
	}
}

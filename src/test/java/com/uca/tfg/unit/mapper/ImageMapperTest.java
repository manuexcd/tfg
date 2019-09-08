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

import com.uca.tfg.dto.ImageDTO;
import com.uca.tfg.mapper.ImageMapperImpl;
import com.uca.tfg.model.Image;

@RunWith(MockitoJUnitRunner.class)
public class ImageMapperTest {

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private ImageMapperImpl mapper;

	@Test
	public void testMapDtoToEntity() {
		ImageDTO dto = new ImageDTO();
		given(modelMapper.map(dto, Image.class)).willReturn(new Image());
		assertNotNull(mapper.mapDtoToEntity(dto));
	}

	@Test
	public void testMapEntityToDto() {
		Image image = new Image();
		given(modelMapper.map(image, ImageDTO.class)).willReturn(new ImageDTO());
		assertNotNull(mapper.mapEntityToDto(image));
	}

	@Test
	public void testMapDtoPageToEntityPage() {
		List<ImageDTO> listDto = new ArrayList<ImageDTO>();
		listDto.add(new ImageDTO());
		assertNotNull(mapper.mapDtoPageToEntityPage(new PageImpl<ImageDTO>(listDto)));
	}

	@Test
	public void testMapEntityPageToDtoPage() {
		List<Image> list = new ArrayList<Image>();
		list.add(new Image());
		assertNotNull(mapper.mapEntityPageToDtoPage(new PageImpl<Image>(list)));
	}

	@Test
	public void testMapDtoListToEntityList() {
		List<ImageDTO> listDto = new ArrayList<ImageDTO>();
		listDto.add(new ImageDTO());
		assertNotNull(mapper.mapDtoListToEntityList(listDto));
	}

	@Test
	public void testMapEntityListToDtoList() {
		List<Image> list = new ArrayList<Image>();
		list.add(new Image());
		assertNotNull(mapper.mapEntityListToDtoList(list));
	}
}
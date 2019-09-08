package com.uca.tfg.mapper;

import org.springframework.stereotype.Component;

import com.uca.tfg.dto.ImageDTO;
import com.uca.tfg.model.Image;

@Component
public class ImageMapperImpl extends GenericMapperImpl<Image, ImageDTO> implements ImageMapper {

	@Override
	public Class<Image> getClazz() {
		return Image.class;
	}

	@Override
	public Class<ImageDTO> getDtoClazz() {
		return ImageDTO.class;
	}

}

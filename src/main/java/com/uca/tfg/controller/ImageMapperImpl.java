package com.uca.tfg.controller;

import com.uca.tfg.dto.ImageDTO;
import com.uca.tfg.mapper.GenericMapperImpl;
import com.uca.tfg.model.Image;

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

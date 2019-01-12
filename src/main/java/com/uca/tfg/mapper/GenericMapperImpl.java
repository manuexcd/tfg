package com.uca.tfg.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericMapperImpl<E, D> implements GenericMapper<E, D> {

	@Autowired
	private ModelMapper mapper;

	public abstract Class<E> getClazz();

	public abstract Class<D> getDtoClazz();

	@Override
	public E mapDtoToEntity(D dto) {
		return mapper.map(dto, getClazz());
	}

	@Override
	public D EntitytoDto(E entity) {
		return mapper.map(entity, getDtoClazz());
	}
}
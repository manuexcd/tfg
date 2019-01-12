package com.uca.tfg.mapper;

public interface GenericMapper<E, D> {
	public E mapDtoToEntity(D dto);
	
	public D EntitytoDto(E entity);
}

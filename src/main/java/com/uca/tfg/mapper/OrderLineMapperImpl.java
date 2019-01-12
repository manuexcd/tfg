package com.uca.tfg.mapper;

import org.springframework.stereotype.Component;

import com.uca.tfg.dto.OrderLineDTO;
import com.uca.tfg.model.OrderLine;

@Component
public class OrderLineMapperImpl extends GenericMapperImpl<OrderLine, OrderLineDTO> implements OrderLineMapper {

	@Override
	public Class<OrderLine> getClazz() {
		return OrderLine.class;
	}

	@Override
	public Class<OrderLineDTO> getDtoClazz() {
		return OrderLineDTO.class;
	}
}

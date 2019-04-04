package com.uca.tfg.mapper;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.uca.tfg.dto.OrderDTO;
import com.uca.tfg.model.Order;

@Component
public class OrderMapperImpl extends GenericMapperImpl<Order, OrderDTO> implements OrderMapper {

	@Override
	public Class<Order> getClazz() {
		return Order.class;
	}

	@Override
	public Class<OrderDTO> getDtoClazz() {
		return OrderDTO.class;
	}

	public void addMapping() {
		super.mapper.addMappings(new PropertyMap<Order, OrderDTO>() {
			@Override
			protected void configure() {
				map().setOrderStatus(source.getOrderStatus());
			}
		});
	}
}

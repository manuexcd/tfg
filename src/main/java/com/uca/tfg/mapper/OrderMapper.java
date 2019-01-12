package com.uca.tfg.mapper;

import org.springframework.stereotype.Component;

import com.uca.tfg.dto.OrderDTO;
import com.uca.tfg.model.Order;

@Component
public interface OrderMapper extends GenericMapper<Order, OrderDTO> {

}

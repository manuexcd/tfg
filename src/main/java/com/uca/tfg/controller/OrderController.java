package com.uca.tfg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.dto.OrderDTO;
import com.uca.tfg.dto.OrderLineDTO;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.mapper.OrderLineMapper;
import com.uca.tfg.mapper.OrderMapper;
import com.uca.tfg.model.Constants;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.service.OrderService;

@RestController
@RequestMapping(value = Constants.PATH_ORDERS)
public class OrderController {

	@Autowired
	private OrderService orderManager;

	@Autowired
	private OrderMapper mapper;

	@Autowired
	private OrderLineMapper orderLineMapper;

	@GetMapping
	public ResponseEntity<Page<OrderDTO>> getAllOrders(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(
				mapper.mapEntityPageToDtoPage(orderManager.getAllOrdersByDate(PageRequest.of(page, pageSize))),
				HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<Page<OrderDTO>> getAllOrdersByOrderStatus(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(
				mapper.mapEntityPageToDtoPage(orderManager.getAllOrdersByOrderStatus(PageRequest.of(page, pageSize))),
				HttpStatus.OK);
	}

	@GetMapping("/{id}/lines")
	public ResponseEntity<List<OrderLineDTO>> getOrderLines(@PathVariable long id) {
		try {
			return new ResponseEntity<>(
					orderLineMapper.mapEntityListToDtoList((List<OrderLine>) orderManager.getOrderLines(id)),
					HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable long id) {
		try {
			return new ResponseEntity<>(mapper.mapEntityToDto(orderManager.getOrder(id)), HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/temporal")
	public ResponseEntity<OrderDTO> getTemporalOrder() {
		try {
			return new ResponseEntity<>(mapper.mapEntityToDto(orderManager.getTemporalOrder()), HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<OrderDTO> deleteOrder(@PathVariable long id) {
		try {
			orderManager.deleteOrder(id);
			return new ResponseEntity<>(mapper.mapEntityToDto(orderManager.getOrder(id)), HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

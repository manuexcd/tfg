package com.uca.tfg.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uca.tfg.exception.NoStockException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.Product;
import com.uca.tfg.model.User;
import com.uca.tfg.repository.OrderRepository;
import com.uca.tfg.repository.ProductRepository;
import com.uca.tfg.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	@Mock
	private OrderRepository dao;

	@Mock
	private UserRepository users;
	
	@Mock
	private ProductRepository products;

	@InjectMocks
	private OrderManagerImp service;
	
	private Pageable pageRequest;

	@Test
	public void testGetAllOrders() {
		given(dao.findAll(eq(pageRequest))).willReturn(Page.empty());
		assertNotNull(service.getAllOrders(pageRequest));
	}

	@Test
	public void testGetAllOrdersByOrderStatus() {
		given(dao.findAllByOrderByOrderStatus(pageRequest)).willReturn(Page.empty());
		assertNotNull(service.getAllOrdersByOrderStatus(pageRequest));
	}

	@Test
	public void testGetAllOrdersByDate() {
		given(dao.findAllByOrderByDate(pageRequest)).willReturn(Page.empty());
		assertNotNull(service.getAllOrdersByDate(pageRequest));
	}

	@Test
	public void testGetOrderById() {
		given(dao.findById(anyLong())).willReturn(Optional.of(new Order()));
		assertNotNull(service.getOrder(anyLong()));
	}

	@Test
	public void testGetOrdersByUser() {
		User user = new User();
		given(users.findById(anyLong())).willReturn(Optional.of(user));
		given(dao.findByUser(user, pageRequest)).willReturn(Page.empty());
		assertNotNull(service.getOrdersByUser(anyLong(), pageRequest));
	}

	@Test
	public void testGetOrderLines() throws OrderNotFoundException {
		Order order = new Order();
		order.setOrderLines(Arrays.asList(new OrderLine()));
		given(dao.findById(anyLong())).willReturn(Optional.of(order));
		assertNotNull(service.getOrderLines(anyLong()));
	}

	@Test(expected = OrderNotFoundException.class)
	public void testGetOrderLinesException() throws OrderNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNull(service.getOrderLines(anyLong()));
	}
	
//	@Test
//	public void testAddOrderLine() throws NoStockException, ProductNotFoundException, OrderNotFoundException {
//		Product product = new Product();
//		product.setStockAvailable(10);
//		product.setPrice(100);
//		products.save(product);
//		given(dao.findById(anyLong())).willReturn(Optional.of(new Order()));
//		given(products.findById(anyLong())).willReturn(Optional.of(product));
//		assertNotNull(service.addOrderLine(1, product.getId(), 10));
//	}
	
	@Test(expected = OrderNotFoundException.class)
	public void testAddOrderLineOrderException() throws NoStockException, ProductNotFoundException, OrderNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNotNull(service.addOrderLine(1, 1, 10));
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void testAddOrderLineProductException() throws NoStockException, ProductNotFoundException, OrderNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.of(new Order()));
		given(products.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNotNull(service.addOrderLine(1, 1, 10));
	}
	
	@Test(expected = NoStockException.class)
	public void testAddOrderLineNoStockException() throws NoStockException, ProductNotFoundException, OrderNotFoundException {
		Product product = new Product();
		product.setStockAvailable(10);
		product.setPrice(100);
		products.save(product);
		given(dao.findById(anyLong())).willReturn(Optional.of(new Order()));
		given(products.findById(anyLong())).willReturn(Optional.of(product));
		assertNotNull(service.addOrderLine(1, product.getId(), 100));
	}
	
	@Test
	public void testDeleteOrder() {
		Order order = new Order();
		dao.save(order);
		service.deleteOrder(order.getId());
		assertFalse(dao.existsById(order.getId()));
	}
}
package com.uca.tfg.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.dao.OrderDAO;
import com.uca.tfg.dao.ProductDAO;
import com.uca.tfg.dao.UserDAO;
import com.uca.tfg.exception.NoStockException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.ProductNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.Product;
import com.uca.tfg.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

	@MockBean
	private OrderDAO dao;

	@MockBean
	private UserDAO users;
	
	@MockBean
	private ProductDAO products;

	@Autowired
	private OrderManager service;

	@Test
	public void testGetAllOrders() {
		given(dao.findAll()).willReturn(Arrays.asList(new Order()));
		assertNotNull(service.getAllOrders());
	}

	@Test
	public void testGetAllOrdersByOrderStatus() {
		given(dao.findAllByOrderByOrderStatus()).willReturn(Arrays.asList(new Order()));
		assertNotNull(service.getAllOrdersByOrderStatus());
	}

	@Test
	public void testGetAllOrdersByDate() {
		given(dao.findAllByOrderByDate()).willReturn(Arrays.asList(new Order()));
		assertNotNull(service.getAllOrdersByDate());
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
		given(dao.findByUser(user)).willReturn(Arrays.asList(new Order()));
		assertNotNull(service.getOrdersByUser(anyLong()));
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
	
	/*@Test
	public void testAddOrderLine() throws NoStockException, ProductNotFoundException, OrderNotFoundException {
		Product product = new Product();
		product.setStockAvailable(10);
		product.setPrice(100);
		products.save(product);
		given(dao.findById(anyLong())).willReturn(Optional.of(new Order()));
		given(products.findById(anyLong())).willReturn(Optional.of(product));
		assertNotNull(service.addOrderLine(1, product.getId(), 10));
	}*/
	
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
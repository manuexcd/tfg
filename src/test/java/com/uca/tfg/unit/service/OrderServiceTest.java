package com.uca.tfg.unit.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.mail.MailSender;
import com.uca.tfg.model.Constants;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.OrderLine;
import com.uca.tfg.model.Product;
import com.uca.tfg.model.User;
import com.uca.tfg.repository.OrderRepository;
import com.uca.tfg.repository.ProductRepository;
import com.uca.tfg.repository.UserRepository;
import com.uca.tfg.service.OrderServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	@Mock
	private OrderRepository dao;

	@Mock
	private UserRepository users;

	@Mock
	private ProductRepository products;

	@Mock
	private MailSender mailSender;

	@InjectMocks
	private OrderServiceImpl service;

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
	public void testGetOrderById() throws OrderNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.of(new Order()));
		assertNotNull(service.getOrder(anyLong()));
	}

	@Test
	public void testGetOrdersByUser() throws UserNotFoundException {
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

	@Test
	public void testGetTemporalOrder() throws OrderNotFoundException {
		Order order = new Order();
		order.setOrderLines(Arrays.asList(new OrderLine()));
		given(dao.findByOrderStatus(anyString())).willReturn(Arrays.asList(order));
		assertNotNull(service.getTemporalOrder());
	}

	@Test(expected = OrderNotFoundException.class)
	public void testGetEmptyTemporalOrder() throws OrderNotFoundException {
		given(dao.findByOrderStatus(anyString())).willReturn(new ArrayList<Order>());
		assertNotNull(service.getTemporalOrder());
	}

	@Test
	public void testCreateTemporalOrder() {
		Order order = new Order();
		Product product = new Product();
		OrderLine orderLine = new OrderLine();
		product.setPrice(10);
		orderLine.setQuantity(2);
		orderLine.setProduct(product);
		order.setOrderLines(Arrays.asList(orderLine));
		given(dao.save(order)).willReturn(order);
		assertNotNull(service.createTemporalOrder(order));
	}

	@Test
	public void testUpdateOrder() throws OrderNotFoundException {
		Order order = new Order();
		User user = new User();
		OrderLine line = new OrderLine();
		Product product = new Product();
		product.setPrice(10);
		line.setProduct(product);
		line.setQuantity(2);
		List<OrderLine> list = new ArrayList<>();
		list.add(line);
		user.setEmail("aaa");
		order.setUser(user);
		order.setOrderStatus(Constants.ORDER_STATUS_RECEIVED);
		order.setOrderLines(list);
		given(dao.save(order)).willReturn(order);
		assertNotNull(service.updateOrder(order));
	}

	@Test
	public void testCancelOrder() throws OrderNotFoundException {
		Order order = new Order();
		User user = new User();
		user.setEmail("aaa");
		order.setUser(user);
		order.setOrderStatus(Constants.ORDER_STATUS_RECEIVED);
		given(dao.findById(anyLong())).willReturn(Optional.of(order));
		given(dao.save(order)).willReturn(order);
		assertNotNull(service.cancelOrder(1));
		assertNotNull(service.cancelOrder(1));
	}

	@Test(expected = OrderNotFoundException.class)
	public void testCancelNullOrder() throws OrderNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.empty());
		service.cancelOrder(1);
	}

	@Test
	public void testCancelOrderBadStatus() throws OrderNotFoundException {
		Order order = new Order();
		User user = new User();
		user.setEmail("aaa");
		order.setUser(user);
		order.setOrderStatus(Constants.ORDER_STATUS_CANCELLED);
		given(dao.findById(anyLong())).willReturn(Optional.of(order));
		given(dao.save(order)).willReturn(order);
		assertNotNull(service.cancelOrder(1));
	}

	@Test
	public void testConfirmTemporalOrder() throws OrderNotFoundException {
		Order order = new Order();
		User user = new User();
		Product product = new Product();
		OrderLine orderLine = new OrderLine();
		product.setStockAvailable(22);
		orderLine.setOrder(order);
		orderLine.setProduct(product);
		orderLine.setQuantity(2);
		user.setEmail("aaa");
		order.setUser(user);
		order.setOrderStatus(Constants.ORDER_STATUS_RECEIVED);
		order.setOrderLines(Arrays.asList(orderLine));
		given(products.findById(anyLong())).willReturn(Optional.ofNullable(product));
		given(dao.save(order)).willReturn(order);
		assertNotNull(service.confirmTemporalOrder(order));
	}

	@Test
	public void testConfirmTemporalOrderNoStock() throws OrderNotFoundException {
		Order order = new Order();
		Product product = new Product();
		OrderLine orderLine = new OrderLine();
		User user = new User();
		user.setEmail("AA");
		product.setStockAvailable(0);
		orderLine.setOrder(order);
		orderLine.setProduct(product);
		orderLine.setQuantity(2);
		order.setOrderLines(Arrays.asList(orderLine));
		order.setUser(user);
		given(products.findById(anyLong())).willReturn(Optional.ofNullable(product));
		assertNull(service.confirmTemporalOrder(order));
	}

	@Test
	public void testDeleteOrder() {
		Order order = new Order();
		dao.save(order);
		service.deleteOrder(order.getId());
		assertFalse(dao.existsById(order.getId()));
	}
}
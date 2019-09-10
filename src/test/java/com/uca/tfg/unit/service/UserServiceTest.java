package com.uca.tfg.unit.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.mail.MailSender;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;
import com.uca.tfg.repository.UserRepository;
import com.uca.tfg.service.OrderService;
import com.uca.tfg.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository dao;

	@Mock
	private BCryptPasswordEncoder encoder;

	@Mock
	private MailSender mailSender;

	@Mock
	private OrderService orderService;

	@InjectMocks
	private UserServiceImpl service;

	private Pageable pageRequest;

	@Test
	public void testRegisterNewUserAccount() throws EmailExistsException {
		User user = new User();
		user.setName("Nombre");
		user.setSurname("Apellidos");
		user.setEmail("aaa");
		given(dao.findByEmail(anyString())).willReturn(user);
		given(dao.existsById(anyLong())).willReturn(false);
		given(dao.save(any())).willReturn(user);
		given(encoder.encode(any())).willReturn("uw9fhew");
		assertNotNull(service.registerNewUserAccount(user));
	}

	@Test(expected = EmailExistsException.class)
	public void testRegisterNewUserAccountException() throws EmailExistsException {
		User user = new User();
		user.setEmail("aaa");
		given(dao.findByEmail(anyString())).willReturn(user);
		given(dao.existsById(anyLong())).willReturn(true);
		assertNotNull(service.registerNewUserAccount(user));
	}

	@Test
	public void testGetAllUsers() {
		given(dao.findByOrderByName(pageRequest)).willReturn(Page.empty());
		assertNotNull(service.getAllUsers(pageRequest));
	}

	@Test
	public void testGetUserById() throws UserNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.of(new User()));
		assertNotNull(service.getUser(anyLong()));
	}

	@Test
	public void testGetUserByEmail() {
		given(dao.findByEmail(anyString())).willReturn(new User());
		assertNotNull(service.getUserByEmail(anyString()));
	}

	@Test
	public void testGetUserByParam() {
		given(dao.findByParam(anyString(), eq(pageRequest))).willReturn(Page.empty());
		assertNotNull(service.getUsersByParam(anyString(), eq(pageRequest)));
	}

	@Test
	public void testGetUserByParamNull() {
		assertNull(service.getUsersByParam(any(), pageRequest));
	}

	@Test
	public void testGetUserOrders() throws UserNotFoundException {
		User user = new User();
		List<Order> orders = new ArrayList<>();
		user.setOrders(orders);
		given(dao.findById(anyLong())).willReturn(Optional.of(user));
		assertNotNull(service.getOrders(anyLong()));
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetUserOrdersException() throws UserNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNull(service.getOrders(anyLong()));
	}

	@Test
	public void testEmailExists() {
		given(dao.findByEmail(anyString())).willReturn(new User());
		given(dao.existsById(anyLong())).willReturn(true);
		assertTrue(service.emailExist("Prueba"));
	}

	@Test
	public void testEmailNotExists() {
		given(dao.findByEmail(anyString())).willReturn(null);
		assertFalse(service.emailExist("Prueba"));
	}

	@Test(expected = UserNotFoundException.class)
	public void testUserNotFound() throws UserNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNull(service.getUser(anyLong()));
	}

	@Test
	public void testCreateTemporalOrder() throws UserNotFoundException {
		User user = new User();
		Order order = new Order();
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(user));
		given(orderService.createTemporalOrder(order)).willReturn(order);
		assertNotNull(service.createTemporalOrder(anyLong(), order));
	}

	@Test(expected = UserNotFoundException.class)
	public void testCreateTemporalOrderUserNotFound() throws UserNotFoundException {
		Order order = new Order();
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		service.createTemporalOrder(anyLong(), order);
	}

	@Test
	public void testUpdateOrder() throws UserNotFoundException {
		User user = new User();
		Order order = new Order();
		order.setUser(user);
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(user));
		given(orderService.updateOrder(order)).willReturn(order);
		assertNotNull(service.updateOrder(anyLong(), order));
	}

	@Test
	public void testUpdateOrder2() throws UserNotFoundException {
		User user = new User();
		Order order = new Order();
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(user));
		given(orderService.updateOrder(order)).willReturn(order);
		assertNotNull(service.updateOrder(anyLong(), order));
	}

	@Test(expected = UserNotFoundException.class)
	public void testUpdateOrderUserNotFound() throws UserNotFoundException {
		Order order = new Order();
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		service.updateOrder(anyLong(), order);
	}

	@Test
	public void testCancelOrder() throws UserNotFoundException, OrderNotFoundException {
		User user = new User();
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(user));
		given(orderService.cancelOrder(anyLong())).willReturn(new Order());
		assertNotNull(service.cancelOrder((long) 1, (long) 1));
	}

	@Test(expected = UserNotFoundException.class)
	public void testCancelOrderUserNotFound() throws UserNotFoundException, OrderNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNotNull(service.cancelOrder((long) 1, (long) 1));
	}

	@Test
	public void testConfirmUser() throws UserNotFoundException, OrderNotFoundException {
		User user = new User();
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(user));
		given(dao.save(user)).willReturn(user);
		assertNotNull(service.confirmUser(anyLong()));
	}

	@Test(expected = UserNotFoundException.class)
	public void testConfirmUserUserNotFound() throws UserNotFoundException, OrderNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNotNull(service.confirmUser(anyLong()));
	}

	@Test
	public void testDeleteUser() {
		User user = new User();
		dao.save(user);
		long id = user.getId();
		service.deleteUser(id);
		assertFalse(dao.existsById(id));
	}

	@Test
	public void testUpdateUser() throws UserNotFoundException {
		User user = new User();
		user.setName("");
		user.setSurname("");
		user.setId(1);
		User userToUpdate = new User();
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(userToUpdate));
		given(dao.save(userToUpdate)).willReturn(userToUpdate);
		assertNotNull(service.updateUser(user));
	}

	@Test(expected = UserNotFoundException.class)
	public void testUpdateUserException() throws UserNotFoundException {
		User user = new User();
		user.setId(1);
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNotNull(service.updateUser(user));
	}
}
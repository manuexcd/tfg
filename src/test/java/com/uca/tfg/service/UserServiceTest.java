package com.uca.tfg.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.dao.UserDAO;
import com.uca.tfg.exception.DuplicateUserException;
import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@MockBean
	private UserDAO dao;

	@Autowired
	private UserManager service;

	@Test
	public void testRegisterNewUserAccount() throws EmailExistsException {
		User user = new User();
		user.setEmail("prueba");
		given(dao.findByEmail(anyString())).willReturn(user);
		given(dao.existsById(anyLong())).willReturn(false);
		given(dao.save(user)).willReturn(user);
		assertNotNull(service.registerNewUserAccount(user));
	}

	@Test(expected = EmailExistsException.class)
	public void testRegisterNewUserAccountException() throws EmailExistsException {
		User user = new User();
		user.setEmail("prueba");
		given(dao.findByEmail(anyString())).willReturn(user);
		given(dao.existsById(anyLong())).willReturn(true);
		given(dao.save(user)).willReturn(user);
		assertNotNull(service.registerNewUserAccount(user));
	}

	@Test
	public void testGetAllUsers() {
		given(dao.findAll()).willReturn(Arrays.asList(new User()));
		assertNotNull(service.getAllUsers());
	}

	@Test
	public void testGetUserById() {
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
		given(dao.findByParam(anyString())).willReturn(Arrays.asList(new User()));
		assertNotNull(service.getUsersByParam(anyString()));
	}

	@Test
	public void testGetUserByParamNull() {
		given(dao.findByParam(anyString())).willReturn(Arrays.asList(new User()));
		assertNotNull(service.getUsersByParam(null));
	}

	@Test
	public void testAddUser() throws DuplicateUserException {
		User user = new User();
		given(dao.existsById(anyLong())).willReturn(false);
		given(dao.save(user)).willReturn(user);
		assertNotNull(service.addUser(user));
	}

	@Test(expected = DuplicateUserException.class)
	public void testAddUserException() throws DuplicateUserException {
		User user = new User();
		given(dao.existsById(anyLong())).willReturn(true);
		given(dao.save(user)).willReturn(user);
		assertNotNull(service.addUser(user));
	}

	@Test
	public void testAddOrder() throws UserNotFoundException {
		User user = new User();
		List<Order> orders = new ArrayList<>();
		user.setOrders(orders);
		given(dao.findById(anyLong())).willReturn(Optional.of(user));
		assertNotNull(service.addOrder(anyLong()));
	}

	@Test(expected = UserNotFoundException.class)
	public void testAddOrderException() throws UserNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNotNull(service.addOrder(anyLong()));
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
		given(dao.findByEmail(anyString())).willReturn(new User());
		given(dao.existsById(anyLong())).willReturn(false);
		assertFalse(service.emailExist("Prueba"));
	}

	@Test
	public void testUserNotFound() {
		given(dao.findById(anyLong())).willReturn(Optional.ofNullable(null));
		assertNull(service.getUser(anyLong()));
	}
	
	@Test
	public void testDeleteUser() {
		User user = new User();
		dao.save(user);
		long id = user.getId();
		service.deleteUser(id);
		assertFalse(dao.existsById(id));
	}
}
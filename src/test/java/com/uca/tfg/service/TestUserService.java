package com.uca.tfg.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.uca.tfg.dao.UserDAO;
import com.uca.tfg.model.User;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	
	@Mock
	private UserDAO users;

	@InjectMocks
	private UserManager service = new UserManagerImp();
	
	@Test
	public void testGetAllUsers() {
		when(service.getAllUsers()).thenReturn(new ArrayList<User>());
		Collection<User> user = service.getAllUsers();
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testGetUserById() {
		when(service.getUser(1)).thenReturn(new User());
		assertNotNull(service.getUser(1));
	}
	
	@Test
	public void testGetUserByEmail() {
		when(service.getUserByEmail("email@email.com")).thenReturn(new User());
		User user = service.getUserByEmail("email@email.com");
		assertNotNull(user);
	}
	
	@Test
	public void testUserNotFound() {
		when(service.getUser(0)).thenReturn(null);
		assertNull(service.getUser(0));
	}
	
	@Test
	public void testGetUserName() {
		User user = mock(User.class);
		when(user.getName()).thenReturn("Name");
		Assert.assertEquals("Name", user.getName());
	}
}
package com.uca.tfg.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.model.User;

import static org.mockito.BDDMockito.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	@MockBean
	private UserManager service;

	@Test
	public void testGetAllUsers() {
		given(service.getAllUsers()).willReturn(Arrays.asList(new User()));
		assertNotNull(service.getAllUsers());
	}

	@Test
	public void testGetUserById() {
		given(service.getUser(Mockito.anyLong())).willReturn(Optional.of(new User()).get());
		assertNotNull(service.getUser(Mockito.anyLong()));
	}

	@Test
	public void testGetUserByEmail() {
		given(service.getUserByEmail(Mockito.anyString())).willReturn(new User());
		assertNotNull(service.getUserByEmail(Mockito.anyString()));
	}

	@Test
	public void testUserNotFound() {
		given(service.getUser(Mockito.anyLong())).willReturn(null);
		assertNull(service.getUser(Mockito.anyLong()));
	}

	/*@Test
	public void testGetUserName() {
		User user = mock(User.class);
		when(user.getName()).thenReturn("Name");
		assertEquals("Name", user.getName());
	}*/
}
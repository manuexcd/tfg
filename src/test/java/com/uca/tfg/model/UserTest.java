package com.uca.tfg.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	
	@Test
	public void constructor() {
		User user = new User("username", "password");
		assertNotNull(user);
	}

	@Test
	public void getFullName() {
		String string = "Prueba";
		User user = new User(string, string, string, string, string, string);
		assertNotNull(user.getFullName());
	}
}

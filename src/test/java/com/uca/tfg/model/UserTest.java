package com.uca.tfg.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserTest {
	
	@Test
	public void constructor() {
		User user = new User("username", "password");
		assertNotNull(user);
	}

	@Test
	public void getFullName() {
		String string = "Prueba";
		User user = new User(string, string, string, string, string, null, null, string, "ROLE_USER");
		assertNotNull(user.getFullName());
	}
}

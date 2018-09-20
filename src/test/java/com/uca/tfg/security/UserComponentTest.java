package com.uca.tfg.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserComponentTest {
	
	@Autowired
	private UserComponent userComponent;
	
	@Test
	public void testUserComponent() {
		User user = new User();
		userComponent.setLoggedUser(user);
		assertNotNull(userComponent.getLoggedUser());
		assertTrue(userComponent.isLoggedUser());
		userComponent.setLoggedUser(null);
		assertFalse(userComponent.isLoggedUser());
	}
}

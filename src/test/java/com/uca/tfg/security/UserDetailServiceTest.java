package com.uca.tfg.security;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.model.User;
import com.uca.tfg.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailServiceTest {

	@MockBean
	private UserRepository dao;

	@Autowired
	private UserDetailsService service;

	@Test
	public void testLoadUserByUsername() {
		User user = new User();
		user.setEmail("email");
		user.setPassword("pass");
		given(dao.findByEmail(anyString())).willReturn(user);
		assertNotNull(service.loadUserByUsername(anyString()));
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testLoadUserByUsernameException() {
		given(dao.findByEmail(anyString())).willReturn(null);
		assertNotNull(service.loadUserByUsername(anyString()));
	}
}

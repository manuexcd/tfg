package com.uca.tfg.security;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.dao.UserDAO;
import com.uca.tfg.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryAuthenticationProviderTest {

	@MockBean
	private UserDAO dao;

	@MockBean
	private Authentication auth;

	@Autowired
	private AuthenticationProvider provider;
	
	/*@Test
	public void testAuthenticate() {
		User user = new User("email", "password");
		given(dao.findByEmail(anyString())).willReturn(user);
		given(auth.getName()).willReturn(user.getEmail());
		given(auth.getCredentials()).willReturn(user.getPassword());
		assertNotNull(provider.authenticate(auth));
	}*/

	@Test(expected = BadCredentialsException.class)
	public void testAuthenticateUserNotFoundException() {
		given(dao.findByEmail(anyString())).willReturn(null);
		assertNotNull(provider.authenticate(auth));
	}
	
	@Test(expected = BadCredentialsException.class)
	public void testAuthenticateWrongPasswordException() {
		User user = new User("email", "password");
		given(dao.findByEmail(anyString())).willReturn(user);
		given(auth.getName()).willReturn(user.getEmail());
		given(auth.getCredentials()).willReturn(user.getPassword());
		assertNotNull(provider.authenticate(auth));
	}
	
	@Test
	public void testSupport() {
		assertTrue(provider.supports(null));
	}
}

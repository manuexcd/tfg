package com.uca.tfg.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AttemptAuthenticationExceptionTest {

	@Test
	public void attemptAuthenticationExceptionTest() {
		assertNotNull(new AttemptAuthenticationException());
	}
}

package com.uca.tfg.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DuplicateUserExceptionTest {

	@Test
	public void duplicateUserException() {
		assertNotNull(new DuplicateUserException());
	}
}

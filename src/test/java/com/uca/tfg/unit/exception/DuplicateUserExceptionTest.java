package com.uca.tfg.unit.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.uca.tfg.exception.DuplicateUserException;

@RunWith(MockitoJUnitRunner.class)
public class DuplicateUserExceptionTest {

	@Test
	public void duplicateUserException() {
		assertNotNull(new DuplicateUserException());
	}
}

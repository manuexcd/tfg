package com.uca.tfg.unit.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.uca.tfg.exception.EmailExistsException;

@RunWith(MockitoJUnitRunner.class)
public class EmailExistsExceptionTest {

	@Test
	public void emailExistsException() {
		assertNotNull(new EmailExistsException(""));
	}
}

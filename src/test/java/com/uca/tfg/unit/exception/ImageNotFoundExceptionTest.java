package com.uca.tfg.unit.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.uca.tfg.exception.ImageNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ImageNotFoundExceptionTest {

	@Test
	public void userNotFoundException() {
		assertNotNull(new ImageNotFoundException());
	}
}

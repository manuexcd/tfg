package com.uca.tfg.unit.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.uca.tfg.exception.NoStockException;

@RunWith(MockitoJUnitRunner.class)
public class NoStockExceptionTest {

	@Test
	public void noStockException() {
		assertNotNull(new NoStockException());
	}
}

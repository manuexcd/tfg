package com.uca.tfg.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.exception.NoStockException;

@RunWith(SpringRunner.class)
public class NoStockExceptionTest {

	@Test
	public void noStockException() {
		assertNotNull(new NoStockException());
	}
}

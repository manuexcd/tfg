package com.uca.tfg.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.exception.OrderNotFoundException;

@RunWith(SpringRunner.class)
public class OrderNotFoundExceptionTest {

	@Test
	public void orderNotFoundException() {
		assertNotNull(new OrderNotFoundException());
	}
}

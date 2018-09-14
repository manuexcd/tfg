package com.uca.tfg.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.exception.OrderLineNotFoundException;

@RunWith(SpringRunner.class)
public class OrderLineNotFoundExceptionTest {

	@Test
	public void orderLineNotFoundException() {
		assertNotNull(new OrderLineNotFoundException());
	}
}
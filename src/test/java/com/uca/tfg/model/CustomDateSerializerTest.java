package com.uca.tfg.model;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RunWith(SpringRunner.class)
public class CustomDateSerializerTest {

	@Test
	public void testCustomDateSerializer() throws IOException {
		CustomDateSerializer cds = new CustomDateSerializer();
		Date date = new Date();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(System.out);
		cds.serialize(date, jsonGenerator, null);
		assertNotNull(date);
	}
}

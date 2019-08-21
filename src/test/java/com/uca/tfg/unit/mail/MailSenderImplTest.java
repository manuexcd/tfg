package com.uca.tfg.unit.mail;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.uca.tfg.mail.MailSenderImpl;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderImplTest {

	@Mock
	private JavaMailSenderImpl emailSender;

	@InjectMocks
	private MailSenderImpl mailSenderImpl;

	@Test
	public void testSendEmail() {
		mailSenderImpl.sendEmail("", "", "", new HashMap<Object, Object>());
	}
}
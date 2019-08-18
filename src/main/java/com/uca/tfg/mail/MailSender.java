package com.uca.tfg.mail;

import java.util.Map;

public interface MailSender {

	public void sendEmail(String to, String subject, String body, Map<Object, Object> params);
}

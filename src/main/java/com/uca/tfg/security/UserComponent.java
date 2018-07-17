package com.uca.tfg.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.uca.tfg.model.User;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserComponent {
	private User user;
	
	public User getLoggedUser() {
		return this.user;
	}
	
	public void setLoggedUser(User user) {
		this.user = user;
	}
	
	public boolean isLoggedUser() {
		return this.user != null;
	}
}
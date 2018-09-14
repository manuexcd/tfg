package com.uca.tfg.security;

public class Constants {

	private Constants() {
	}

	// Spring Security

	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT

	public static final String SUPER_SECRET_KEY = "SecretKeyToGenJWTs";
	public static final long TOKEN_EXPIRATION_TIME = 864000;
}
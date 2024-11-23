package com.authenticate.jwt.exception;

public class UsernameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4016108163897003744L;

	public UsernameNotFoundException(String message) {
		super(message);
	}

	public UsernameNotFoundException(Throwable cause) {
		super(cause);
	}

	public UsernameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}

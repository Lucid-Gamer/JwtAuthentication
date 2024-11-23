package com.authenticate.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.authenticate.jwt.payload.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> usernameNotFoundExceptionHandler(UsernameNotFoundException ex) {
		return new ResponseEntity<ApiResponse<Object>>(new ApiResponse<Object>(ex.getMessage(), null, false, "-1"),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> exceptionHandler(Exception ex) {
		return new ResponseEntity<ApiResponse<Object>>(new ApiResponse<Object>(ex.getMessage(), null, false, "-500"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

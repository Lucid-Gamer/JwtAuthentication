package com.authenticate.jwt.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
	
	private String errorMsg;
	
	private T respObject;
	
	private boolean flag;
	
	private String errorCode;

}

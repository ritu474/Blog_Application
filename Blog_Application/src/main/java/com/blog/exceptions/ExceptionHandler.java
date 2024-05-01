package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.ApiResponse;

@RestControllerAdvice   //This class handles the exceptions in RESTful web services and returns a JSON response
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception)
	{
		String message=exception.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> argumentNotValidExceptionHandler(MethodArgumentNotValidException exception)
	{
		Map<String,String> response=new HashMap<>();
		//Will give a list of all error object
		exception.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName= ((FieldError)error).getField();
			String message =error.getDefaultMessage();
			response.put(fieldName, message);
		
		});
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}
	
}

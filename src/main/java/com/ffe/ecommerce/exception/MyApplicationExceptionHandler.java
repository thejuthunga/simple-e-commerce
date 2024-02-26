package com.ffe.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ffe.ecommerce.dto.ResponseStructure;


@ControllerAdvice
public class MyApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(IdDoesNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> catchIdDoesNotPresentException(IdDoesNotPresentException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ResponseStructure<String>> catchNullPointerException(NullPointerException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setData("Something Wrong in your request");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
}

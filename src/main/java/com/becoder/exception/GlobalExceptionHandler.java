package com.becoder.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.becoder.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception e) {

		ErrorResponse error = ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("You doing operation with Null value").build();
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?> handleMissingParameterException(Exception e) 
	{

		ErrorResponse error = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST.value())
				.message(e.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) 
	{
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		
		Map<String, String> error=new LinkedHashMap<>();
		
		allErrors.stream().forEach(er ->{
			
			String message = er.getDefaultMessage();
			String field = ((FieldError)(er)).getField();
			error.put(field,message);
		});
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
	    ErrorResponse error = ErrorResponse.builder()
	            .status(HttpStatus.BAD_REQUEST.value())
	            .message("Invalid request body: " + ex.getMostSpecificCause().getMessage())
	            .build();
	    
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	

}
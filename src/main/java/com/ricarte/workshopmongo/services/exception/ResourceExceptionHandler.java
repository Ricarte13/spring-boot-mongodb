package com.ricarte.workshopmongo.services.exception;

import java.time.Instant;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ricarte.workshopmongo.resources.exception.StandardError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), "Not found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}

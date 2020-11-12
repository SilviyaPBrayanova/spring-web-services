package com.in28minutes.rest.webservices.restful_web_services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserInformation extends RuntimeException {

	private static final long serialVersionUID = 1406290972379490141L;

	public InvalidUserInformation(String message) {
		super(message);
	}
}
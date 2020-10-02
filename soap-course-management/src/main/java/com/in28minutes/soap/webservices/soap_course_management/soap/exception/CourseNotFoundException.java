package com.in28minutes.soap.webservices.soap_course_management.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://in28minutes.com/courses}001_CURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -7244961732951797879L;

	public CourseNotFoundException(String message) {
		super(message);
	}
}
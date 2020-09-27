package com.in28minutes.soap.webservices.soap_course_management.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {
	
	@PayloadRoot(namespace = "http://in28minutes.com/courses", 
				 localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest courseDetailsRequest) {
		GetCourseDetailsResponse courseDetailsResponse = new GetCourseDetailsResponse();
		
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(courseDetailsRequest.getId());
		courseDetails.setName("Microservices Course");
		courseDetails.setDescription("That would be a wonderful course");
		courseDetailsResponse.setCourseDetails(courseDetails);
		return courseDetailsResponse;
	}
}

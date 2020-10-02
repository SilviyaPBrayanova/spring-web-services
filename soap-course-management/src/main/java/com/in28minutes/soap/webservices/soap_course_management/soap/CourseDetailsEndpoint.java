package com.in28minutes.soap.webservices.soap_course_management.soap;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.DeleteCourseDetailsRequest;
import com.in28minutes.courses.DeleteCourseDetailsResponse;
import com.in28minutes.courses.GetAllCoursesDetailsRequest;
import com.in28minutes.courses.GetAllCoursesDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.in28minutes.soap.webservices.soap_course_management.soap.bean.Course;
import com.in28minutes.soap.webservices.soap_course_management.soap.exception.CourseNotFoundException;
import com.in28minutes.soap.webservices.soap_course_management.soap.service.CourseDetailsService;
import com.in28minutes.soap.webservices.soap_course_management.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService courseDetailsService;
	
	@PayloadRoot(namespace = "http://in28minutes.com/courses", 
				 localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest courseDetailsRequest) {
		Course course = courseDetailsService.findById(courseDetailsRequest.getId());
		if(course == null) {
			throw new CourseNotFoundException("Course does not exist id: " + courseDetailsRequest.getId());
		}
		GetCourseDetailsResponse courseDetailsResponse = new GetCourseDetailsResponse();
		courseDetailsResponse.setCourseDetails(mapCourse(course));
		return courseDetailsResponse;
	}
	
	@PayloadRoot(namespace = "http://in28minutes.com/courses", 
			 localPart = "GetAllCoursesDetailsRequest")
	@ResponsePayload
	public GetAllCoursesDetailsResponse processAllCoursesDetailsRequest(@RequestPayload GetAllCoursesDetailsRequest allCoursesDetailsRequest) {
		List<Course> courses = courseDetailsService.findAll();
		GetAllCoursesDetailsResponse response = new GetAllCoursesDetailsResponse();
		response.getCourseDetails().addAll(mapAllCourses(courses));
		return response;
	}
	
	@PayloadRoot(namespace = "http://in28minutes.com/courses", 
			 localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse processDeleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest deleteCourseDetailsRequest) {
		Status status = courseDetailsService.deleteById(deleteCourseDetailsRequest.getId());
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
	}

	private List<CourseDetails> mapAllCourses(List<Course> courses){
		return courses.stream()
					  .map(course -> mapCourse(course))
					  .collect(Collectors.toList());
	}
	
	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	private com.in28minutes.courses.Status mapStatus(Status status){
		if (status == Status.SUCCESS)
			return com.in28minutes.courses.Status.SUCCESS;
		return com.in28minutes.courses.Status.FAILURE;
	}
}

package com.in28minutes.rest.webservices.restful_web_services.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping(path = "/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("field1", "field2", "field3");
		return filterFields(someBean, "field1", "field2");
	}
	
	@GetMapping(path = "/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> list = Arrays.asList(new SomeBean("field1", "field2", "field3"),
				 new SomeBean("field1_2", "field2_2", "field3_2"));
		return filterFields(list, "field1", "field3");
	}
	
	private MappingJacksonValue filterFields(Object toFilter, String... properties) {
		SimpleBeanPropertyFilter filters = SimpleBeanPropertyFilter.filterOutAllExcept(properties);
		FilterProvider filterProvider  = new SimpleFilterProvider().addFilter("SomeBeanFilter", filters);
		MappingJacksonValue mapping = new MappingJacksonValue(toFilter);
		mapping.setFilters(filterProvider);
		return mapping;
	}
}

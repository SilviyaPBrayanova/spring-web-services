package com.in28minutes.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Carlie");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Carlie"));
	}

	@GetMapping(value = "person/param", params = "version=v1")
	public PersonV1 personRequestParamV1() {
		return new PersonV1("Bob Carlie");
	}
	
	@GetMapping(value = "person/param", params = "version=v2")
	public PersonV2 personRequestParamV2() {
		return new PersonV2(new Name("Bob", "Carlie"));
	}
	
	@GetMapping(value = "person/header", headers = "X-API-VERSION=1")
	public PersonV1 personHeaderParamV1() {
		return new PersonV1("Bob Carlie");
	}
	
	@GetMapping(value = "person/header", headers = "X-API-VERSION=2")
	public PersonV2 personHeaderParamV2() {
		return new PersonV2(new Name("Bob", "Carlie"));
	}
	
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 personProducerParamV1() {
		return new PersonV1("Bob Carlie");
	}
	
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 personProducerParamV2() {
		return new PersonV2(new Name("Bob", "Carlie"));
	}
}
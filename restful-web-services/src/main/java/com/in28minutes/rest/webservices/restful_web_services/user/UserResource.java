package com.in28minutes.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.in28minutes.rest.webservices.restful_web_services.exception.InvalidUserInformation;

@RestController
public class UserResource {

	@Autowired
	private UserDAOService usersService;
	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return usersService.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = usersService.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		//HATEOAS - Hypermedia As The Engine Of Application State
		EntityModel<User> resource = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(link.withRel("all-users"));
		return resource;
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		if(user == null || user.getName() == null || user.getName().equals("") || user.getBirthDate() == null) {
			throw new InvalidUserInformation("name = " + user.getName() + ", birthDate = " + user.getBirthDate());
		}
		User savedUser = usersService.save(user);
		
		URI location = ServletUriComponentsBuilder
					   .fromCurrentRequest()
					   .path("/{id}")
					   .buildAndExpand(savedUser.getId())
					   .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		User deletedUser = usersService.deleteById(id);
		if(deletedUser == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return ResponseEntity.noContent().build();
		
	}
}
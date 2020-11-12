package com.in28minutes.rest.webservices.restful_web_services.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<>();
	private static AtomicInteger usersCount = new AtomicInteger(3);
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null)
			user.setId(usersCount.incrementAndGet());
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		return users.stream().filter(u -> u.getId()==id).findFirst().orElse(null);
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User u = iterator.next();
			if(u.getId() == id) {
				iterator.remove();
				return u;
			}
		}
		return null;
	}
}

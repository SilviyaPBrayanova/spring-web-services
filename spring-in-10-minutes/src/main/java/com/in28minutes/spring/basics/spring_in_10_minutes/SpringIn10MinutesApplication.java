package com.in28minutes.spring.basics.spring_in_10_minutes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringIn10MinutesApplication {

	// What are the beans? -> @Component
	
	// What are the dependencies of beans? -> @Autowired
	
	// Where to search for beans? -> @SpringBootApplication 
	// automatic scan of the package and sub-packages of the annotated class
	
	public static void main(String[] args) {
//		BinarySearchImpl bs = 
//				new BinarySearchImpl(new BubbleSortArgorithm());
//		int result = bs.binarySearch(new int[] {8, 3, 4, 6, 124}, 6);
//		System.out.println(result);
		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringIn10MinutesApplication.class, args);
		BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);
		int result = binarySearchImpl.binarySearch(new int[] {8, 3, 4, 6, 124}, 6);
		System.out.println(result);
	}
}
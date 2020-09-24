package com.in28minutes.springboot.basics.springboot_in_10_steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootIn10StepsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootIn10StepsApplication.class, args);
		
		for(String beanName : applicationContext.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}
	}

}

package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurity404Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity404Application.class, args);
	}


	@Bean
	public CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository) throws Exception {
		return (String[] args) -> {
			User user = new User("bart", "bart@domain.com", "bart", "Bart", "Simpson", true);
			Role userRole = new Role("bart", "ROLE_USER");
			userRepository.save(user);
			roleRepository.save(userRole);
			User admin = new User("admin", "super@domain.com", "admin", "Super", "Man", true);
			Role adminRole = new Role("admin", "ROLE_ADMIN");
			//How to dispaly twp roles of the same user added on for 405
			Role adminRole2 = new Role("admin", "ROLE_USER");
			userRepository.save(admin);
			roleRepository.save(adminRole);
			roleRepository.save(adminRole2);
		};
	}

}

package com.computec.dev.outh2;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.computec.dev.oauth2.user.model.User;
import com.computec.dev.oauth2.user.repository.UserRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"com.computec.dev.oauth2.user.repository.UserRepository"})
public class SpringOauth2Application implements CommandLineRunner {
	
	@Autowired(required = false)
	private UserRepository userRepository;

	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringOauth2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (this.userRepository.findByUsername("admin") == null) {
			User user = new User("Jose Son", "admin", passwordEncoder.encode("admin123"), Arrays.asList("ADMIN"));

			this.userRepository.save(user);
		}
	}

}

package cs.ku.sa_project;

import cs.ku.sa_project.entities.Customer;
import cs.ku.sa_project.entities.Staff;
import cs.ku.sa_project.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SaProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(SaProjectApplication.class, args);
	}
	// Insert Staff
}

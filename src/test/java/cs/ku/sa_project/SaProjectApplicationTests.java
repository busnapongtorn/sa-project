package cs.ku.sa_project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SaProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void generatePasswordHash() {
		// Put the password you want to hash here
		String rawPassword = "staff1";

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode(rawPassword);

		// This will print the hash to your console
		System.out.println("---------------------------------");
		System.out.println("Hashed Password: " + hashedPassword);
		System.out.println("---------------------------------");
	}
}

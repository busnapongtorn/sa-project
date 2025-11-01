package cs.ku.sa_project.services;

import cs.ku.sa_project.dto.LoginRequest;
import cs.ku.sa_project.dto.LoginResponse;
import cs.ku.sa_project.dto.RegisterRequest;
import cs.ku.sa_project.entities.Customer;
import cs.ku.sa_project.entities.User;
import cs.ku.sa_project.repositories.CustomerRepository;
import cs.ku.sa_project.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());

        // User not found
        if (user.isEmpty()) {
            return new LoginResponse(false, 0, null);
        }

        User userFound = user.get();
        String role = userFound.getRole();
        Customer customer = customerRepository.findByUsername(userFound.getUsername());
        Long customerId = customer.getCustomerId();

        if(passwordEncoder.matches(loginRequest.getPassword(), userFound.getPassword())){
            return new LoginResponse(true, customerId, role);
        }else{
            return new LoginResponse(false, 0, role);
        }
    }

    public User register(RegisterRequest registerRequest) {
        // Check for duplicate username
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        // Create Customer
        Customer customer = new Customer();
        customer.setUsername(registerRequest.getUsername());
        customer.setEmail(registerRequest.getEmail());
        customer.setFirstName(registerRequest.getFirstName());
        customer.setLastName(registerRequest.getLastName());
        customer.setPhoneNumber(registerRequest.getPhoneNumber());
        customerRepository.save(customer);

        // Create User
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("Customer");
        return userRepository.save(user);
    }
}

package cs.ku.sa_project.services;

import cs.ku.sa_project.dto.RegisterRequest;
import cs.ku.sa_project.entities.User;
import cs.ku.sa_project.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User register(RegisterRequest registerRequest) {
        // Check for duplicate username
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setRole("Customer");
        return userRepository.save(user);
    }
}

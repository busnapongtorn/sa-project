package cs.ku.sa_project.controllers;

import cs.ku.sa_project.dto.LoginRequest;
import cs.ku.sa_project.dto.LoginResponse;
import cs.ku.sa_project.dto.RegisterRequest;
import cs.ku.sa_project.entities.User;
import cs.ku.sa_project.repositories.UserRepository;
import cs.ku.sa_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest) {
       return userService.register(registerRequest);
    }
}

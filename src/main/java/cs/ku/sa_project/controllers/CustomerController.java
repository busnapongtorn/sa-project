package cs.ku.sa_project.controllers;
import cs.ku.sa_project.dto.ProfileUpdate;
import cs.ku.sa_project.entities.User;
import cs.ku.sa_project.entities.Customer;
import cs.ku.sa_project.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // <-- Tells Spring this is an API controller
@RequestMapping("/api/customers") // <-- All URLs in this class start with /api/items
@CrossOrigin(origins = "http://localhost:3000") // <-- Allows Next.js to connect
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public void sendInvoice(String email){}

    public void sendTracking(String email){}

    public void validate(){}

    public User createNewUser(){return new User();}

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
            Customer customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody ProfileUpdate profileUpdate) {
            return ResponseEntity.ok(customerService.updateCustomer(id, profileUpdate));
    }
}

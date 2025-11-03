package cs.ku.sa_project.services;

import cs.ku.sa_project.dto.ProfileUpdate;
import cs.ku.sa_project.entities.Customer;
import cs.ku.sa_project.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserService userService;

    public Customer getCustomerById(Long customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + customerId));
    }

    public Customer updateCustomer(Long customerId, ProfileUpdate profileUpdate){
        Customer customer = getCustomerById(customerId);
        String oldUsername = customer.getUsername();
        customer.setUsername(profileUpdate.getUsername());
        customer.setEmail(profileUpdate.getEmail());
        customer.setFirstName(profileUpdate.getFirstName());
        customer.setLastName(profileUpdate.getLastName());
        customer.setPhoneNumber(profileUpdate.getPhoneNumber());
        // Update username in user table
        userService.updateUsername(oldUsername, profileUpdate.getUsername());
        return customerRepository.save(customer);
    }
}

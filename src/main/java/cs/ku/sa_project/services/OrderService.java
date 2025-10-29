package cs.ku.sa_project.services;

import cs.ku.sa_project.entities.Item;
import cs.ku.sa_project.entities.Order;
import cs.ku.sa_project.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAllByOrderByOrderIdAsc();
    }

    public Order getOrderById(Long orderId) {
        return  orderRepository.findByOrderId(orderId);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order setAwaitPayment(Long orderId) {
        // Find the existing item or throw an error if not found
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        // Update status to "Awaiting Payment"
        order.setStatus("Awaiting Payment");
        // Save the updated order back to the database
        return orderRepository.save(order);
    }

    public Order paymentCompleted(Long orderId) {
        // Find the existing item or throw an error if not found
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        // Update status to "Awaiting Payment"
        order.setStatus("Payment Completed");
        // Save the updated order back to the database
        return orderRepository.save(order);
    }

    public Order packingCompleted(Long orderId) {
        // Find the existing item or throw an error if not found
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        // Update status to "Awaiting Payment"
        order.setStatus("Packing Completed");
        // Save the updated order back to the database
        return orderRepository.save(order);
    }
}

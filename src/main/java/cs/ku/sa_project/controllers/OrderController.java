package cs.ku.sa_project.controllers;
import cs.ku.sa_project.entities.CartList;
import cs.ku.sa_project.entities.Customer;
import cs.ku.sa_project.entities.Item;
import cs.ku.sa_project.entities.Order;
import cs.ku.sa_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    private OrderService orderService;

    public Order createOrder(Customer customer, CartList cartList) {
        return new Order();
    }

    public void setAwaitPayment(Order order) {
    }

    public void paymentCompleted(String order_id) {
    }

    public void packingCompleted(String order_id) {
    }

    public void orderShipped(Order order) {
    }

    public void sendTracking(String tracking_no) {
    }

    @GetMapping
    public List<Order> getData() {
        return orderService.getOrders();
    }

    @GetMapping
    public Order getOrderFromId(Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    public Order createorder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{orderId}") // Handles PUT requests like /api/items/123
    public ResponseEntity<Order> setAwaitPayment(
            @PathVariable Long orderId       // Gets the ID (123) from the URL path
    ) {
        try {
            Order savedOrder = orderService.setAwaitPayment(orderId);
            return ResponseEntity.ok(savedOrder); // Return 200 OK + updated order
        } catch (RuntimeException e) {
            System.err.println("Error updating order: " + e.getMessage());
            // Return 404 Not Found if the service threw an error (e.g., item not found)
            return ResponseEntity.notFound().build();
        }
    }
}

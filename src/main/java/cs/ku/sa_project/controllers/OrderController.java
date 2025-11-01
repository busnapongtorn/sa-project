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
import java.util.Map;

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

//    @GetMapping("/orderId/")
//    public Order getOrderFromId(@RequestParam("id") Long orderId) {
//        return orderService.getOrderById(orderId);
//    }

    @GetMapping("/orderId")
    public List<Order> getOrdersFromCustomerId(@RequestParam("id") Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @PostMapping
    public Order createorder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody Map<String, String> body) {

        String newStatus = body.get("status");
        try {
            Order updatedOrder = orderService.updateOrderStatus(orderId, newStatus);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{orderId}/trackingNo")
    public ResponseEntity<Order> updateOrderTrackingNo(
            @PathVariable Long orderId,
            @RequestBody Map<String, String> body) {

        String newStatus = body.get("trackingNo");
        try {
            Order updatedOrder = orderService.updateOrderTrackingNo(orderId, newStatus);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

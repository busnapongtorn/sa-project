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
    @Autowired
    private InvoiceService invoiceService;

    public List<Order> getOrders() {
        return orderRepository.findAllByOrderByOrderIdAsc();
    }

    public Order getOrderById(Long orderId) {
        return  orderRepository.findByOrderId(orderId);
    }

    public Order createOrder(Order order) {
        Order orderSaved = orderRepository.save(order);
        invoiceService.generateInvoice(orderSaved);
        return orderSaved;
    }

    public Order updateOrderStatus(Long orderId, String status) {
        // Find the existing item or throw an error if not found
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        // Update fields from the incoming data
        order.setStatus(status);
        // Save the updated item back to the database
        return orderRepository.save(order);
    }

    public Order updateOrderTrackingNo(Long orderId, String trackingNo) {
        // Find the existing item or throw an error if not found
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        // Update fields from the incoming data
        order.setTrackingNo(trackingNo);
        // Save the updated item back to the database
        return orderRepository.save(order);
    }
}

package cs.ku.sa_project.services;

import cs.ku.sa_project.dto.SalesDataDto;
import cs.ku.sa_project.entities.Item;
import cs.ku.sa_project.entities.Order;
import cs.ku.sa_project.entities.Invoice;
import cs.ku.sa_project.repositories.InvoiceRepository;
import cs.ku.sa_project.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private InvoiceRepository invoiceRepository;
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

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    public Order createOrder(Order order) {
        order.setStatus("Awaiting Payment");
        Order orderSaved = orderRepository.save(order);
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

    public SalesDataDto getSalesData(LocalDate startDate, LocalDate endDate) {
        Instant startInstant = startDate.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant endInstant = endDate.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);
        List<Order> orders = orderRepository.findByOrderDateBetween(startInstant, endInstant);
        int orderTotal = orders.size();

        int awaitingPayment = (int) orders.stream()
                .filter(o -> "Awaiting Payment".equals(o.getStatus()))
                .count();

        int readyToShip = (int) orders.stream()
                .filter(o -> "Ready to Ship".equals(o.getStatus()))
                .count();

        int completed = (int) orders.stream()
                .filter(o -> "Completed".equals(o.getStatus()))
                .count();

        double totalIncome = orders.stream()
                .filter(o -> "Completed".equals(o.getStatus()))
                .mapToDouble(this::getOrderTotalFromInvoice)
                .sum();

        return new SalesDataDto(orderTotal, awaitingPayment, readyToShip, completed, totalIncome);
    }

    private double getOrderTotalFromInvoice(Order order) {
        Invoice invoice =  invoiceRepository.findInvoiceByOrderId(order.getOrderId());
        return invoice.getTotalAmount();
    }
}

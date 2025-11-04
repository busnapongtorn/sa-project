package cs.ku.sa_project.services;

import cs.ku.sa_project.entities.*;
import cs.ku.sa_project.repositories.InvoiceRepository;
import cs.ku.sa_project.repositories.ItemRepository;
import cs.ku.sa_project.repositories.OrderItemRepository;
import cs.ku.sa_project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private CustomerService customerService;

    public Invoice generateInvoice(Order order, double totalAmount) {
        Invoice newInvoice = new Invoice(order, totalAmount);
        return invoiceRepository.save(newInvoice);
    }

    public Invoice getInvoiceByOrderId(long orderId) {
        return invoiceRepository.findInvoiceByOrderId(orderId);
    }

    public Order paidInvoice(Long orderId, String receiptNo){
        // Change Order status to "Paid"
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Invoice invoice = invoiceRepository.findInvoiceByOrderId(order.getOrderId());
        itemService.deductStock(orderId);

        // Set invoice to Paid
        order.setStatus("Paid");

        // Set receiptNo of invoice
        invoice.setReceiptNo(receiptNo);
        invoice.setStatus("Paid");
        invoiceRepository.save(invoice);
        orderRepository.save(order);
        return order;
    }

    public Order unpaidInvoice(Long orderId, Long customerId){
        // Change Order status to "Paid"
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Invoice invoice = invoiceRepository.findInvoiceByOrderId(order.getOrderId());
        List<OrderItems> orderItems = orderItemRepository.findAllByOrderId(order.getOrderId());

        // Set invoice to Paid
        order.setStatus("Awaiting Payment");

        // add stock quantity, add reserve items
        for (OrderItems orderItem : orderItems) {
            Item item = itemRepository.findById(orderItem.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));
            item.setReservedQuantity(orderItem.getQuantity());
            item.setStockQuantity(item.getStockQuantity() + orderItem.getQuantity());
            itemRepository.save(item);
        }

        // Send email to customer that payment failed
        Customer customer = customerService.getCustomerById(customerId);
        emailSenderService.sendEmail(customer.getEmail(), "Payment failed", "Payment fa" +
                "iled for order id : " + order.getOrderId() + "\nPlease go into the website and pay again");

        // Set receiptNo of invoice
        invoice.setReceiptNo("");
        invoice.setStatus("Unpaid");
        invoiceRepository.save(invoice);
        orderRepository.save(order);
        return order;
    }
}


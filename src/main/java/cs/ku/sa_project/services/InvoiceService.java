package cs.ku.sa_project.services;

import cs.ku.sa_project.entities.Invoice;
import cs.ku.sa_project.entities.Order;
import cs.ku.sa_project.entities.OrderItems;
import cs.ku.sa_project.repositories.InvoiceRepository;
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

    public Invoice generateInvoice(Order order, double totalAmount) {
        Invoice newInvoice = new Invoice(order, totalAmount);
        return invoiceRepository.save(newInvoice);
    }

    public Invoice getInvoiceByOrderId(long orderId) {
        return invoiceRepository.findInvoiceByOrderId(orderId);
    }

    public Order paidInvoice(Long orderId, String receiptNo){
        // Change Order status to "Paid"
        Order order = orderRepository.findByOrderId(orderId);
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
}


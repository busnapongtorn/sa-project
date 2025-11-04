package cs.ku.sa_project.controllers;

import cs.ku.sa_project.entities.Customer;
import cs.ku.sa_project.entities.Invoice;
import cs.ku.sa_project.entities.Order;
import cs.ku.sa_project.services.InvoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // <-- Tells Spring this is an API controller
@RequestMapping("/api/invoices") // <-- All URLs in this class start with /api/items
@CrossOrigin(origins = "http://localhost:3000") // <-- Allows Next.js to connect
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

//    @PostMapping
//    public Invoice generateInvoice(Order order){ // Needs to add customer
//        return invoiceService.generateInvoice(order, 0);
//    }

    public Invoice getInvoiceFromDate(String date_s, String date_e){
        return new Invoice();
    }

    public void addSum(){};

    @PatchMapping("/paid/{orderId}")
    public ResponseEntity<Order> onInvoicePaid(
            @PathVariable Long orderId, // 1. Get the orderId from the URL
            @RequestBody Map<String, String> body // 2. Get the JSON body as a Map
    ) {
        Order order = invoiceService.paidInvoice(orderId, body.get("receiptNo"));
        return ResponseEntity.ok(order);
    }

    @GetMapping("/totalAmount/{orderId}")
    public ResponseEntity<Double> getTotalAmountByOrderId(@PathVariable Long orderId){
        Invoice invoice = invoiceService.getInvoiceByOrderId(orderId);
        return ResponseEntity.ok(invoice.getTotalAmount());
    }

    @GetMapping("/receiptNo/{orderId}")
    public ResponseEntity<String> getReceiptNoByOrderId(@PathVariable Long orderId){
        Invoice invoice = invoiceService.getInvoiceByOrderId(orderId);
        return ResponseEntity.ok(invoice.getReceiptNo());
    }

    @PatchMapping("/unpaid/{orderId}")
    public ResponseEntity<Order> onInvoiceUnpaid(
            @PathVariable Long orderId,
            @RequestBody Map<String, Object> body
    ){
        Long customerId = ((Number) body.get("customerId")).longValue();
        Order order = invoiceService.unpaidInvoice(orderId, customerId);
        return ResponseEntity.ok(order);
    }
}

package cs.ku.sa_project.controllers;

import cs.ku.sa_project.entities.Customer;
import cs.ku.sa_project.entities.Invoice;
import cs.ku.sa_project.entities.Order;
import cs.ku.sa_project.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // <-- Tells Spring this is an API controller
@RequestMapping("/api/invoices") // <-- All URLs in this class start with /api/items
@CrossOrigin(origins = "http://localhost:3000") // <-- Allows Next.js to connect
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public Invoice generateInvoice(Order order){ // Needs to add customer
        return invoiceService.generateInvoice(order);
    }

    public Invoice getInvoiceFromDate(String date_s, String date_e){
        return new Invoice();
    }

    public void addSum(){};
    public void onInvoicePaid(){};
}

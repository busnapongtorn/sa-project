package cs.ku.sa_project.services;

import cs.ku.sa_project.entities.Invoice;
import cs.ku.sa_project.entities.Order;
import cs.ku.sa_project.repositories.InvoiceRepository;
import cs.ku.sa_project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Invoice generateInvoice(Order order){
        return invoiceRepository.save(new Invoice(order));
    }
}


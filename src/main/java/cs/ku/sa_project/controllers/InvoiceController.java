package cs.ku.sa_project.controllers;

import cs.ku.sa_project.entities.Customer;
import cs.ku.sa_project.entities.Invoice;
import cs.ku.sa_project.entities.Order;

public class InvoiceController {
    public Invoice generateInvoice(Customer customer, Order order){
        return new Invoice();
    }

    public Invoice getInvoiceFromDate(String date_s, String date_e){
        return new Invoice();
    }

    public void addSum(){};
    public void onInvoicePaid(){};
}

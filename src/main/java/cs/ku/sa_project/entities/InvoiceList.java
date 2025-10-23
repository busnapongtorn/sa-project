package cs.ku.sa_project.entities;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class InvoiceList {
    private ArrayList<Invoice> invoices;

    public ArrayList<Invoice> createList(){return new ArrayList<Invoice>();}
    public void addInvoiceToList(Invoice invoice){}
}

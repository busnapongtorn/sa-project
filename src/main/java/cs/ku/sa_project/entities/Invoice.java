package cs.ku.sa_project.entities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {
    private String invoice_id;
    private String receipt_no;
    private String date;
    private String due_date;
    private String payment_method;
    private String status;
    private double total_amount;
}

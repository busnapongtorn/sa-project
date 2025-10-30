package cs.ku.sa_project.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    private String receiptNo;
    private String date;
    private String dueDate;
    private String paymentMethod;
    private String status;
    private double totalAmount;
    private long orderId; //FK

    public Invoice(Order order){
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.receiptNo = "";
        this.date = date;
        this.dueDate = "";
        this.paymentMethod = "";
        this.status = "Unpaid";
        this.totalAmount = 0;
        this.orderId = order.getOrderId();
    }
}

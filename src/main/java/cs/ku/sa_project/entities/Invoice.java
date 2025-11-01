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
import java.util.Random;
import java.util.stream.Collectors;


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
        String randomString = new Random().ints(10, 0, 10)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining());
        this.receiptNo = randomString;
        this.date = date;
        this.dueDate = "";
        this.paymentMethod = "";
        this.status = "Unpaid";
        this.totalAmount = 0;
        this.orderId = order.getOrderId();
    }
}

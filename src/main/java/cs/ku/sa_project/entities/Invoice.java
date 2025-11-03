package cs.ku.sa_project.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
    private String status;
    private double totalAmount;
    private long orderId; //FK

    public Invoice(Order order, double totalAmount){
//        String date = order.getOrderDate();
//        Instant orderDate = Instant.parse(date);
        Instant orderDate = order.getOrderDate();
        Instant dueDate = orderDate.plus(5, ChronoUnit.DAYS);
        String dueDateString = dueDate.toString();
//        String randomString = new Random().ints(10, 0, 10)
//                .mapToObj(Integer::toString)
//                .collect(Collectors.joining());
        this.receiptNo = "";
        this.date = date;
        this.dueDate = dueDateString;
        this.status = "Unpaid";
        this.totalAmount = totalAmount;
        this.orderId = order.getOrderId();
    }

}

package cs.ku.sa_project.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String status;
    private String confirmationDate;
    private String cancelReason;
    private String address;
    private String orderDate;
    private String trackingNo;
    private String paymentStatus;

    public void setTrackingNo(String tracking_no){

    }
}

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
    private Long order_id;
    private String status;
    private String confirmation_date;
    private String cancel_reason;
    private String address;
    private String order_date;
    private String tracking_no;
    private String payment_status;

    public void setTrackingNo(String tracking_no){

    }
}

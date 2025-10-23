package cs.ku.sa_project.entities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Order {
    private String order_id;
    private String order_status_history;
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

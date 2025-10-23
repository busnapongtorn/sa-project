package cs.ku.sa_project.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private String customer_id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String bankacc_number;

    public void sendTrackingDetail(String tracking_no){}
    public void display(){}
}



package cs.ku.sa_project.entities;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Staff {
    private String staff_id;
    private String username;
    private String password;
    private String role;
    private String department;
    private String email;

    public void setPickingList(ArrayList<OrderItems> OrderItems){}
}

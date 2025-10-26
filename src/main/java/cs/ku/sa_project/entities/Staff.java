package cs.ku.sa_project.entities;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staff_id;
    private String username;
    private String password;
    private String role;
    private String department;
    private String email;

    public void setPickingList(ArrayList<OrderItems> OrderItems){}
}

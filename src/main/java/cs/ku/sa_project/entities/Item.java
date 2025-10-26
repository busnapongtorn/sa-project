package cs.ku.sa_project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;
    private String size;
    private int stock_quantity;
    private int reserved_quantity;
    private String itemName;
    private double current_price;
    private String status;

    public void deductItem(int quantity){

    }
}

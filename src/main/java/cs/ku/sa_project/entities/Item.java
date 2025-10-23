package cs.ku.sa_project.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String item_id;
    private String size;
    private int stock_quantity;
    private int reserved_quantity;
    private String item_name;
    private double current_price;
    private String status;

    public void deductItem(int quantity){

    }
}

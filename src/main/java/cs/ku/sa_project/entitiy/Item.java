package cs.ku.sa_project.entitiy;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Item {
    private String item_name;
    private int item_id;
    private int stock_quantity;
    private int current_price;
    private int reserved_quantity;
    private int size;
    private int status;
}

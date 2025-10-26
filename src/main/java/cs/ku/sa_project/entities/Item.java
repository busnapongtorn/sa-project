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
    private Long itemId;
    private String size;
    private int stockQuantity;
    private int reservedQuantity;
    private String itemName;
    private double currentPrice;
    private String status;

    public void deductItem(int quantity){

    }
}

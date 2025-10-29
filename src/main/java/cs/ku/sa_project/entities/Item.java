package cs.ku.sa_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderItems> orderItems;

    public void deductItem(int quantity){

    }
}

package cs.ku.sa_project.entities;

import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@IdClass(OrderItemId.class)
public class OrderItems {
    @Id
    private Long itemId;
    @Id
    private Long orderId;
    private int quantity;
    private double totalPrice;

    public OrderItems(Order order, CartList cartList){

    }
}

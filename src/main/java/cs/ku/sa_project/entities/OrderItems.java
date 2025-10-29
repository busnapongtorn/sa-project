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
    @Column(name = "item_id")
    private Long itemId;
    @Id
    @Column(name = "order_id")
    private Long orderId;

    private int quantity;
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
}

package cs.ku.sa_project.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class OrderItemDto {
    private Long itemId;
    private Long orderId;
    private int quantity;
    private double totalPrice;
}

package cs.ku.sa_project.dto;

import cs.ku.sa_project.entities.OrderItems;
import lombok.Data;

@Data
public class OrderItemResponseDto {
    private Long itemId;
    private int quantity;
    private double totalPrice;
    private String itemName;

    public OrderItemResponseDto(OrderItems orderItem) {
        this.itemId = orderItem.getItemId();
        this.quantity = orderItem.getQuantity();
        this.totalPrice = orderItem.getTotalPrice();

        // Safely get the name from the nested item
        if (orderItem.getItem() != null) {
            this.itemName = orderItem.getItem().getItemName();
        } else {
            this.itemName = "Item Not Found";
        }
    }
}

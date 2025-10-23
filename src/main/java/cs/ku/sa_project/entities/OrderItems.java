package cs.ku.sa_project.entities;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderItems {
    private ArrayList<Item> orderItems = new ArrayList<Item>();
    private int quantity;
    private double unit_price;
    private double total_price;
    private String order_id;

    public OrderItems(Order order, CartList cartList){

    }
}

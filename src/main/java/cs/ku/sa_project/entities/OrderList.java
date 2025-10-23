package cs.ku.sa_project.entities;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderList {
    private ArrayList<Order> orders;

    public ArrayList<Order> createList(){
        ArrayList<Order> orders = new ArrayList<>();
        return orders;
    }

    public void addOrderToList(Order order){

    }

    public Order getOrder(String id){
        Order order = new Order();
        return order;
    }
}

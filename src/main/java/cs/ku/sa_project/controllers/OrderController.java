package cs.ku.sa_project.controllers;
import cs.ku.sa_project.entities.CartList;
import cs.ku.sa_project.entities.Customer;
import cs.ku.sa_project.entities.Order;

import java.util.ArrayList;


public class OrderController {
    public Order createOrder(Customer customer, CartList cartList){return new Order();}
    public void setAwaitPayment(Order order){}
    public void paymentCompleted(String order_id){}
    public void packingCompleted(String order_id){}
    public void orderShipped(Order order){}
    public void sendTracking(String tracking_no){}

    public ArrayList<Order> getData(){
        ArrayList<Order> newOrderList = new ArrayList<>();
        return newOrderList;
    }

    public Order getOrderFromId(String order_id){
        Order newOrder = new Order();
        return newOrder;
    }
}

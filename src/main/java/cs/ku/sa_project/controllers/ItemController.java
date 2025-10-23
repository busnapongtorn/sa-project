package cs.ku.sa_project.controllers;
import cs.ku.sa_project.entities.CartList;
import cs.ku.sa_project.entities.Item;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;

import java.util.ArrayList;

public class ItemController {
    public ArrayList<Item> search(String query){return new ArrayList<>();}
    public void displayPopup(Item item){}
    public void OpenCustomerCart(CartList cartList){}
    public void deleteItem(CartList cartList, Item item){}
    public void confirmCart(CartList cartList){}
    public void reserveItems(Item item, int quantity){}
    public void deductStock(ArrayList<Item> orderItems){}
    public ArrayList<Item> getData(){return new ArrayList<Item>();}
    // public void search(String query){}
    public Item createItem(){
        return new Item();
    }
    public void updateItemDetails(){}
    public boolean checkReserved(Item item){return true;}
    public void discontinueItem(Item item){}
}

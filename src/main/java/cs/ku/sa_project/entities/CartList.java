package cs.ku.sa_project.entities;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartList {
    private ArrayList<Item> cartList;

    public void addToCartList(Item item) {}
    public void deleteItem(Item item){}
}

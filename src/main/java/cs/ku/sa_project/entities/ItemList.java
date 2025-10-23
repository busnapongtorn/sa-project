package cs.ku.sa_project.entities;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ItemList {
    public ArrayList<Item> createList(){
        ArrayList<Item> items = new ArrayList<>();
        return items;
    }

    public void addItems(Item item){};
    public void addToList(Item item){};
    public void sortFromQuery(String query){};
    public void removeItemFromList(Item item){};
}

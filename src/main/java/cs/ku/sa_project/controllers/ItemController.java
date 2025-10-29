package cs.ku.sa_project.controllers;
import cs.ku.sa_project.entities.CartList;
import cs.ku.sa_project.entities.Item;
import cs.ku.sa_project.repositories.ItemRepository;
import cs.ku.sa_project.services.ItemService;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RestController // <-- Tells Spring this is an API controller
@RequestMapping("/api/items") // <-- All URLs in this class start with /api/items
@CrossOrigin(origins = "http://localhost:3000") // <-- Allows Next.js to connect
public class ItemController {
    @Autowired // <-- Asks Spring to inject the ItemService
    private ItemService itemService;

    @GetMapping("/search")
    public List<Item> search(@RequestParam("q") String query){
        return itemService.searchItems(query);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item){
        return itemService.createItem(item);
    }

    @GetMapping
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @PutMapping("/{itemId}") // Handles PUT requests like /api/items/123
    public ResponseEntity<Item> updateItem(
            @PathVariable Long itemId,       // Gets the ID (123) from the URL path
            @RequestBody Item updatedItemData // Gets the updated data from the request body
    ) {
        try {
            Item savedItem = itemService.updateItem(itemId, updatedItemData);
            return ResponseEntity.ok(savedItem); // Return 200 OK + updated item
        } catch (RuntimeException e) {
            System.err.println("Error updating item: " + e.getMessage());
            // Return 404 Not Found if the service threw an error (e.g., item not found)
            return ResponseEntity.notFound().build();
        }
    }

    // public void displayPopup(Item item){}
    public void OpenCustomerCart(CartList cartList){}
    public void deleteItem(CartList cartList, Item item){}
    public void confirmCart(CartList cartList){}
    public void reserveItems(Item item, int quantity){}
    public void deductStock(ArrayList<Item> orderItems){}
    public ArrayList<Item> getData(){return new ArrayList<Item>();}
    // public void search(String query){}
    public boolean checkReserved(Item item){return true;}
    public void discontinueItem(Item item){}
}

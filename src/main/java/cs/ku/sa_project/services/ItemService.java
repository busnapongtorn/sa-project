package cs.ku.sa_project.services;

import cs.ku.sa_project.entities.Item;
import cs.ku.sa_project.repositories.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service // <-- Tells Spring this is a Service (a "bean")
public class ItemService {

    @Autowired // <-- Asks Spring to inject the database repository
    private ItemRepository itemRepository;

    public List<Item> searchItems(String query) {
        if (query == null || query.trim().isEmpty()) {
            return itemRepository.findAll();
        } else {
           return itemRepository.findByItemNameContainingIgnoreCase(query);
        }
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getItems() {
        return itemRepository.findAll(); // Use built-in JpaRepository method
    }

    public Item updateItem(Long itemId, Item updatedItemData) {
        // Find the existing item or throw an error if not found
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + itemId));

        // Update fields from the incoming data
        item.setItemName(updatedItemData.getItemName());
        item.setCurrent_price(updatedItemData.getCurrent_price());
        item.setSize(updatedItemData.getSize());
        item.setStock_quantity(updatedItemData.getStock_quantity());
        item.setReserved_quantity(updatedItemData.getReserved_quantity());
        item.setStatus(updatedItemData.getStatus());
        // Save the updated item back to the database
        return itemRepository.save(item);
    }
}
package cs.ku.sa_project.repositories;

import cs.ku.sa_project.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * This is a "magic" method. Spring Data JPA understands it:
     * "FindBy..."     -> "SELECT * FROM item WHERE..."
     * "ItemName"      -> "...item_name..." (if your Java field is 'itemName')
     * "Containing"    -> "...LIKE %query%..."
     * "IgnoreCase" -> "...and make it case-insensitive"
     */
    List<Item> findByItemNameContainingIgnoreCase(String item_name);
    List<Item> findAllByOrderByItemIdAsc();
    int countByStatus(String status);
    @Query("SELECT SUM(i.reservedQuantity) FROM Item i")
    Long sumReservedQuantity();
    @Query("SELECT SUM(i.stockQuantity) FROM Item i")
    Long sumStockQuantity();
    List<Item> findAllByStatusOrderByItemIdAsc(String status);
}
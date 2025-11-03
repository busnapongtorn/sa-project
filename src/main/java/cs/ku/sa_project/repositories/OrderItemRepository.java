package cs.ku.sa_project.repositories;

import cs.ku.sa_project.entities.OrderItemId;
import cs.ku.sa_project.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItems, OrderItemId> {
    List<OrderItems> findAllByOrderId(Long orderId);
    @Query("SELECT oi FROM OrderItems oi JOIN FETCH oi.item WHERE oi.orderId = :orderId")
    List<OrderItems> findByOrderIdWithItem(@Param("orderId") Long orderId);
}

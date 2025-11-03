package cs.ku.sa_project.repositories;

import cs.ku.sa_project.entities.OrderItemId;
import cs.ku.sa_project.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItems, OrderItemId> {
    List<OrderItems> findAllByOrderId(Long orderId);
}

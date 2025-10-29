package cs.ku.sa_project.repositories;

import cs.ku.sa_project.entities.OrderItemId;
import cs.ku.sa_project.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItems, OrderItemId> {

}

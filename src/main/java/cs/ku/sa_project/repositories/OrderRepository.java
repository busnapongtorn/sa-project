package cs.ku.sa_project.repositories;

import cs.ku.sa_project.entities.Item;
import cs.ku.sa_project.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByOrderIdAsc();
    Order findByOrderId(Long orderId);
    List<Order> findAllByCustomerId(Long customerId);
    List<Order> findByOrderDateBetween(Instant startDate, Instant endDate);
}

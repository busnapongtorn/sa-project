package cs.ku.sa_project.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
// Springboot method to use composite key
public class OrderItemId implements Serializable {
    private Long orderId;
    private Long itemId;
}

package cs.ku.sa_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockDataDto {
    private long itemTotal;
    private long itemAvailable;
    private long itemDiscontinued;
    private long totalReservedQuantity;
    private long totalStockQuantity;
}

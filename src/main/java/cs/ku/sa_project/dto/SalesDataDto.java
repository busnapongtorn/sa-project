package cs.ku.sa_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesDataDto {
    private int orderTotal;
    private int awaitingPayment;
    private int readyToShip;
    private int completed;
    private double totalIncome;
}

package cs.ku.sa_project.services;

import cs.ku.sa_project.dto.OrderItemDto;
import cs.ku.sa_project.entities.Item;
import cs.ku.sa_project.entities.Order;
import cs.ku.sa_project.entities.OrderItems;
import cs.ku.sa_project.repositories.ItemRepository;
import cs.ku.sa_project.repositories.OrderItemRepository;
import cs.ku.sa_project.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    @Lazy
    private InvoiceService invoiceService;

    public List<OrderItems> getOrderItems() {
        return orderItemRepository.findAll(); // Use built-in JpaRepository method
    }

    public List<OrderItems> createOrderItems(List<OrderItemDto> orderItemDtos) {
        List<OrderItems> orderItems = new ArrayList<>();
        OrderItemDto orderItemsDto = orderItemDtos.getFirst();
        Order order = orderRepository.findById(orderItemsDto.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find order"));
        double orderTotal = 0;
        for (OrderItemDto orderItemDto : orderItemDtos) {
            OrderItems orderItem = new OrderItems();
            Item item = itemRepository.findById(orderItemDto.getItemId())
                    .orElseThrow(() -> new EntityNotFoundException("Cannot find item"));
            orderItem.setItem(item);
            orderItem.setOrder(order);
            orderItem.setOrderId(order.getOrderId());
            orderItem.setItemId(item.getItemId());
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setTotalPrice(orderItemDto.getTotalPrice());
            orderItems.add(orderItem);
            orderTotal += orderItem.getTotalPrice(); // get data for invoice generation
            // Reserve Items
            itemService.reserveItems(item, orderItem);
        }
        invoiceService.generateInvoice(order, orderTotal);
        return orderItemRepository.saveAll(orderItems);
    }
}
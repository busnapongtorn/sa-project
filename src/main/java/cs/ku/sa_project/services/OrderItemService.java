package cs.ku.sa_project.services;

import cs.ku.sa_project.dto.OrderItemDto;
import cs.ku.sa_project.dto.OrderItemResponseDto;
import cs.ku.sa_project.entities.*;
import cs.ku.sa_project.repositories.ItemRepository;
import cs.ku.sa_project.repositories.OrderItemRepository;
import cs.ku.sa_project.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private CustomerService customerService;
    StringBuilder emailBody = new StringBuilder().append("Order Items List : \n");

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
            // Append email body
            emailBody.append(orderItem.getQuantity()).append(" ").append(item.getItemName()).append(" : ")
                    .append(item.getCurrentPrice()).append(" * ").append(orderItem.getQuantity()).append(" = ").append(orderItem.getTotalPrice())
                    .append(" THB").append("\n");
        }
        Invoice invoice = invoiceService.generateInvoice(order, orderTotal);
        emailBody.append("Total : ").append(orderTotal).append(" THB\n").append("Please pay before ").append(invoice.getDueDate()).append("\n");
        // Send mail
        Customer customer = customerService.getCustomerById(order.getCustomerId());
        emailSenderService.sendEmail(customer.getEmail(), "New Invoice from Foam Groupnine Store", emailBody.toString());
        return orderItemRepository.saveAll(orderItems);
    }

    public List<OrderItems> getOrderItems(long orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }

    public List<OrderItemResponseDto> getOrderItemsResponse(long orderId) {
        List<OrderItems> orderItems = orderItemRepository.findByOrderIdWithItem(orderId);

        return orderItems.stream()
                .map(OrderItemResponseDto::new)
                .collect(Collectors.toList());
    }
}
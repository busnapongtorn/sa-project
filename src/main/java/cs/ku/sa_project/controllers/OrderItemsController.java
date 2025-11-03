package cs.ku.sa_project.controllers;

import cs.ku.sa_project.dto.OrderItemDto;
import cs.ku.sa_project.dto.OrderItemResponseDto;
import cs.ku.sa_project.entities.Item;
import cs.ku.sa_project.entities.Order;
import cs.ku.sa_project.entities.OrderItems;
import cs.ku.sa_project.services.ItemService;
import cs.ku.sa_project.services.OrderItemService;
import cs.ku.sa_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order_items")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderItemsController {
    @Autowired
    OrderItemService orderItemService;

    @GetMapping
    public List<OrderItems> getOrderItems() {
        return orderItemService.getOrderItems();
    }

    @PostMapping
    public List<OrderItems> createOrderItems(@RequestBody List<OrderItemDto> orderItemDtos) {
        return orderItemService.createOrderItems(orderItemDtos);
    }

    @GetMapping("/itemName/{orderId}")
    public List<OrderItemResponseDto> getOrderItemsResponse(@PathVariable("orderId") Long orderId) {
        return orderItemService.getOrderItemsResponse(orderId);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItems> getOrderItems(@PathVariable("orderId") Long orderId) {
        return orderItemService.getOrderItems(orderId);
    }
}

package com.zedapp.orderservice.service;

import com.zedapp.orderservice.dto.OrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<List<OrderDto>> getOrders();
    ResponseEntity<OrderDto> getOrder(String id);
    ResponseEntity<OrderDto> getOrderByInternalId(String internalId);
    ResponseEntity<OrderDto> create(OrderDto orderDto);
    ResponseEntity<OrderDto> update(String id, OrderDto orderDto);
    ResponseEntity deleteById(String id);
    ResponseEntity deleteAll();
    ResponseEntity<OrderDto> assignElement(String orderId, String elementId);
}

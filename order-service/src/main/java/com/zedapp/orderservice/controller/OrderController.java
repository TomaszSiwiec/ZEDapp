package com.zedapp.orderservice.controller;

import com.zedapp.orderservice.dto.OrderDto;
import com.zedapp.orderservice.service.implementation.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/zedapp/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<OrderDto>> getAll() {
        return orderService.getOrders();
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable(value = "id") String id) {
        return orderService.getOrder(id);
    }

    @GetMapping(value = "/getByIID/{id}")
    public ResponseEntity<OrderDto> getByInternalId(@PathVariable(value = "id") String id) {
        return orderService.getOrderByInternalId(id);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) {
        return orderService.create(orderDto);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<OrderDto> update(@PathVariable(value = "id") String id,
                                           OrderDto orderDto) {
        return orderService.update(id, orderDto);
    }

    @PutMapping(value = "/assignElement/{orderInternalId}/{elementId}")
    public ResponseEntity<OrderDto> assignElement(@PathVariable(value = "orderInternalId") String orderInternalId,
                                                  @PathVariable(value = "elementId") String elementId) {
        return orderService.assignElement(orderInternalId, elementId);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") String id) {
        return orderService.deleteById(id);
    }

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity deleteAll() {
        return orderService.deleteAll();
    }
}

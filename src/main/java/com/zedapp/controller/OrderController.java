package com.zedapp.controller;

import com.zedapp.domain.dto.OrderDto;
import com.zedapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/zedapp/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAll")
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/get")
    public OrderDto getById(@RequestParam(value = "id") Long id) {
        return orderService.get(id);
    }

    @GetMapping("/getAllByPurchaserId")
    public List<OrderDto> getByPurchaserId(@RequestParam(value = "purchaserId") Long purchaserId) {
        return orderService.getByPurchaserId(purchaserId);
    }

    @PostMapping("/create")
    public OrderDto create(@RequestBody OrderDto orderDto) {
        return orderService.create(orderDto);
    }

    @PutMapping("/update")
    public OrderDto update(@RequestParam(value = "id") Long id, @RequestBody OrderDto orderDto) {
        return orderService.update(id, orderDto);
    }

    @PutMapping("/assignElement")
    public OrderDto assignElement(@RequestParam(value = "orderId") Long orderId, @RequestParam(value = "elementId") Long elementId) {
        return orderService.assignElement(orderId, elementId);
    }

    @PutMapping("/assignUser")
    public OrderDto assignUser(@RequestParam(value = "orderId") Long orderId, @RequestParam(value = "userId") Long userId) {
        return orderService.assignUser(orderId, userId);
    }

    @PutMapping("/assignPurchaser")
    public OrderDto assignPurchaser(@RequestParam(value = "orderId") Long orderId, @RequestParam(value = "purchaserId") Long purchaserId) {
        return orderService.assignPurchaser(orderId, purchaserId);
    }

    @DeleteMapping("/deleteElement")
    public void deleteElement(@RequestParam(name = "orderId") Long orderId, @RequestParam(name = "elementId") Long elementId) {
        orderService.deleteElementFromOrder(orderId, elementId);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "id") Long id) {
        orderService.delete(id);
    }
}

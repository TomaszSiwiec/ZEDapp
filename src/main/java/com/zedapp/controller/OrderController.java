package com.zedapp.controller;

import com.zedapp.domain.dto.OrderDto;
import com.zedapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PutMapping("/update")
    public OrderDto update(@RequestParam(value = "id") Long id, @RequestBody OrderDto orderDto) {
        return orderService.update(id, orderDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "id") Long id) {
        orderService.delete(id);
    }
}

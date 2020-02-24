package com.zedapp.service;

import com.zedapp.domain.Order;
import com.zedapp.domain.dto.OrderDto;
import com.zedapp.mapper.OrderMapper;
import com.zedapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderDto> getAll() {
        return orderMapper.mapToDtoList(orderRepository.findAll());
    }

    public OrderDto get(long id) {
        Order order = orderRepository.findOrThrow(id);
        return orderMapper.mapToDto(order);
    }

    public OrderDto create(OrderDto orderDto) {
        Order order = new Order();
        order.setName(orderDto.getName());
        order.setComments(orderDto.getComments());
        order.setDateOfCreation(LocalDateTime.now());
        return orderMapper.mapToDto(orderRepository.save(order));
    }

    public OrderDto update(long id, OrderDto orderDto) {
        Order order = orderRepository.findOrThrow(id);
        order.setComments(orderDto.getComments());
        order.setName(orderDto.getName());
        order.setDateOfCreation(orderDto.getDateOfCreation());
        return orderMapper.mapToDto(orderRepository.save(order));
    }

    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}

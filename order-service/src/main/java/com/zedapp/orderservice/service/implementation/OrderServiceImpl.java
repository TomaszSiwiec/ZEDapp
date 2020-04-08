package com.zedapp.orderservice.service.implementation;

import com.zedapp.orderservice.domain.Order;
import com.zedapp.orderservice.dto.ElementDto;
import com.zedapp.orderservice.dto.FileDto;
import com.zedapp.orderservice.dto.OrderDto;
import com.zedapp.orderservice.exception.ObjectNotFoundException;
import com.zedapp.orderservice.mapper.OrderMapper;
import com.zedapp.orderservice.repository.OrderRepository;
import com.zedapp.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> ordersDtos = orderMapper.mapToOrderDtoList(orders);
        return new ResponseEntity<>(ordersDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDto> getOrder(String id) {
        try {
            Order order = orderRepository.findOrThrow(id);
            OrderDto orderDto = orderMapper.mapToOrderDto(order);
            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<OrderDto> getOrderByInternalId(String internalId) {
        try {
            Order order = orderRepository.findByInternalIdOrThrow(internalId);
            OrderDto orderDto = orderMapper.mapToOrderDto(order);
            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<OrderDto> create(OrderDto orderDto) {
        try {
            Order order = new Order();
            order.setInternalId(findMaxInternalId() + 1);
            order.setComment(orderDto.getComment());
            order.setPurchaserId(orderDto.getPurchaser().getId());
            order.setFilesIds(getFilesIds(orderDto.getFiles()));
            order.setElementsIds(getElementsIds(orderDto.getElements()));
            orderRepository.save(order);
            return new ResponseEntity<>(orderMapper.mapToOrderDto(order), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    private int findMaxInternalId() throws NoSuchElementException {
        List<Order> orders = orderRepository.findAll();
        List<Integer> ids = orders.stream()
                .map(order -> order.getInternalId())
                .collect(Collectors.toList());
        return ids.stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public ResponseEntity<OrderDto> update(String id, OrderDto orderDto) {
        try {
            Order order = orderRepository.findOrThrow(id);
            order.setPurchaserId(orderDto.getPurchaser().getId());
            order.setComment(orderDto.getComment());
            order.setElementsIds(getElementsIds(orderDto.getElements()));
            order.setFilesIds(getFilesIds(orderDto.getFiles()));
            orderRepository.save(order);
            return new ResponseEntity<>(orderMapper.mapToOrderDto(order), HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private List<String> getElementsIds(List<ElementDto> elementDtos) {
        return elementDtos.stream()
                .map(elementDto -> elementDto.getId())
                .collect(Collectors.toList());
    }

    private List<String> getFilesIds(List<FileDto> fileDtos) {
        return fileDtos.stream()
                .map(fileDto -> fileDto.getId())
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity deleteById(String id) {
        try {
            Order order = orderRepository.findOrThrow(id);
            orderRepository.deleteById(order.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteAll() {
        orderRepository.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }
}

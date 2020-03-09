package com.zedapp.service;

import com.zedapp.domain.Element;
import com.zedapp.domain.Order;
import com.zedapp.domain.Purchaser;
import com.zedapp.domain.User;
import com.zedapp.domain.dto.OrderDto;
import com.zedapp.mapper.ElementMapper;
import com.zedapp.mapper.OrderMapper;
import com.zedapp.repository.ElementRepository;
import com.zedapp.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import com.zedapp.repository.PurchaserRepository;
import com.zedapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaserRepository purchaserRepository;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderDto> getAll() {
        log.info("[ZEDAPP] Returned all object from entity ORDERS");
        return orderMapper.mapToDtoList(orderRepository.findAll());
    }

    public OrderDto get(long id) {
        Order order = orderRepository.findOrThrow(id);
        log.info("[ZEDAPP] Returned object with ID: " + id + " from entity ORDERS");
        return orderMapper.mapToDto(order);
    }

    public OrderDto create(OrderDto orderDto) {
        Order order = new Order();
        order.setName(orderDto.getName());
        order.setComments(orderDto.getComments());
        order.setDateOfCreation(LocalDateTime.now());
        log.info("[ZEDAPP] Added new object with name: " + order.getName() + " to entity ORDERS");
        return orderMapper.mapToDto(orderRepository.save(order));
    }

    public OrderDto update(long id, OrderDto orderDto) {
        Order order = orderRepository.findOrThrow(id);
        order.setComments(orderDto.getComments());
        order.setName(orderDto.getName());
        order.setDateOfCreation(orderDto.getDateOfCreation());
        log.info("[ZEDAPP] Updated object with ID: " + id +  " from entity ORDERS");
        return orderMapper.mapToDto(orderRepository.save(order));
    }

    public OrderDto assignElement(Long orderId, Long elementId) {
        Element element = elementRepository.findOrThrow(elementId);
        Order order = orderRepository.findOrThrow(orderId);
        List<Element> elementsOfOrder = order.getElements();
        elementsOfOrder.add(element);
        order.setElements(elementsOfOrder);
        element.setOrder(order);
        elementRepository.save(element);
        return orderMapper.mapToDto(orderRepository.save(order));
    }

    public void delete(long id) {
        orderRepository.deleteById(id);
        log.info("[ZEDAPP] Deleted object with ID: " + id + " from entity ORDERS");
    }

    public void deleteElementFromOrder(Long orderId, Long elementId) {
        Order order = orderRepository.findOrThrow(orderId);
        Element element = elementRepository.findOrThrow(elementId);
        List<Element> elements = order.getElements();
        elements.remove(element);
        order.setElements(elements);
        orderRepository.save(order);
        elementRepository.delete(element);
    }

    public OrderDto assignUser(Long orderId, Long userId) {
        Order order = orderRepository.findOrThrow(orderId);
        User user = userRepository.findOrThrow(userId);
        List<Order> orders = user.getOrders();
        orders.add(order);
        order.setAddedBy(user);
        user.setOrders(orders);
        userRepository.save(user);
        return orderMapper.mapToDto(orderRepository.save(order));
    }

    public OrderDto assignPurchaser(Long orderId, Long purchaserId) {
        Order order = orderRepository.findOrThrow(orderId);
        Purchaser purchaser = purchaserRepository.findOrThrow(purchaserId);
        List<Purchaser> purchasers = order.getPurchasers();
        purchasers.add(purchaser);
        order.setPurchasers(purchasers);
        List<Order> purchasersOrders = purchaser.getOrders();
        purchasersOrders.add(order);
        purchaser.setOrders(purchasersOrders);
        purchaserRepository.save(purchaser);
        return orderMapper.mapToDto(orderRepository.save(order));
    }

    public List<OrderDto> getByPurchaserId(Long purchaserId) {
        Purchaser purchaser = purchaserRepository.findOrThrow(purchaserId);
        List<Order> orders = orderRepository.findAll();
        List<Order> filteredOrders = orders.stream()
                .filter(order -> order.getPurchasers().contains(purchaser))
                .collect(Collectors.toList());
        return orderMapper.mapToDtoList(filteredOrders);
    }
}

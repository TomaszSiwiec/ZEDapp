package com.zedapp.mapper;

import com.zedapp.domain.Order;
import com.zedapp.domain.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private ElementMapper elementMapper;

    @Autowired
    private PurchaserMapper purchaserMapper;

    @Autowired
    private UserMapper userMapper;

    public OrderDto mapToDto(Order order) {
        if (order == null)
            return null;
        return new OrderDto(
                order.getId(),
                order.getName(),
                order.getComments(),
                order.getDateOfCreation(),
                elementMapper.mapToDtoList(order.getElements()),
                purchaserMapper.mapToDtoList(order.getPurchasers()),
                userMapper.mapToDto(order.getAddedBy())
        );
    }

    public Order mapToEntity(OrderDto orderDto) {
        if (orderDto == null)
            return null;
        return new Order(
                orderDto.getId(),
                orderDto.getName(),
                orderDto.getComments(),
                orderDto.getDateOfCreation(),
                elementMapper.mapToEntityList(orderDto.getElementDtos()),
                purchaserMapper.mapToEntityList(orderDto.getPurchaserDtos()),
                userMapper.mapToEntity(orderDto.getAddedByDto())
        );
    }

    public List<OrderDto> mapToDtoList(List<Order> orderList) {
        if (orderList == null)
            return null;

        return orderList.stream()
                .map(order -> mapToDto(order))
                .collect(Collectors.toList());
    }

    public List<Order> mapToEntityList(List<OrderDto> orderDtoList) {
        if (orderDtoList == null)
            return null;

        return orderDtoList.stream()
                .map(orderDto -> mapToEntity(orderDto))
                .collect(Collectors.toList());
    }
}

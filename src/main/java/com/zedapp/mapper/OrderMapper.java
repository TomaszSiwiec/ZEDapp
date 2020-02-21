package com.zedapp.mapper;

import com.zedapp.domain.Order;
import com.zedapp.domain.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public OrderDto mapToDto(Order order) {
        if (order == null)
            return null;
        return new OrderDto(
                order.getName(),
                order.getComments(),
                order.getDateOfCreation()
        );
    }

    public Order mapToEntity(OrderDto orderDto) {
        if (orderDto == null)
            return null;
        return new Order(
                0L,
                orderDto.getName(),
                orderDto.getComments(),
                orderDto.getDateOfCreation(),
                null,
                null,
                null
        );
    }

    public List<OrderDto> mapToDtoList(List<Order> orderList) {
        if (orderList.isEmpty())
            return new ArrayList<>();
        if (orderList == null)
            return null;

        return orderList.stream()
                .map(order -> mapToDto(order))
                .collect(Collectors.toList());
    }

    public List<Order> mapToEntityList(List<OrderDto> orderDtoList) {
        if (orderDtoList.isEmpty())
            return new ArrayList<>();
        if (orderDtoList == null)
            return null;

        return orderDtoList.stream()
                .map(orderDto -> mapToEntity(orderDto))
                .collect(Collectors.toList());
    }
}

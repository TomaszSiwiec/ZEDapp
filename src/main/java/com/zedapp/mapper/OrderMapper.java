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

    public OrderDto mapToDto(Order order) {
        if (order == null)
            return null;
        return new OrderDto(
                order.getId(),
                order.getName(),
                order.getComments(),
                order.getDateOfCreation()
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
                null,
                null,
                null
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

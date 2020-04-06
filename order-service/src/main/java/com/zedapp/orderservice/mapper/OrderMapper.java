package com.zedapp.orderservice.mapper;

import com.zedapp.orderservice.domain.Order;
import com.zedapp.orderservice.dto.OrderDto;
import com.zedapp.orderservice.service.implementation.ElementServiceImpl;
import com.zedapp.orderservice.service.implementation.FileServiceImpl;
import com.zedapp.orderservice.service.implementation.PurchaserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private PurchaserServiceImpl purchaserServiceImpl;

    @Autowired
    private FileServiceImpl fileServiceImpl;

    @Autowired
    private ElementServiceImpl elementServiceImpl;

    public Order mapToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setPurchaserId(orderDto.getPurchaser().getId());
        order.setComment(orderDto.getComment());

        List<String> elementsIds = orderDto.getElements()
                .stream()
                .map(elementDto -> elementDto.getId())
                .collect(Collectors.toList());

        List<String> filesIds = orderDto.getFiles()
                .stream()
                .map(fileDto -> fileDto.getId())
                .collect(Collectors.toList());
        order.setElementsIds(elementsIds);
        order.setFilesIds(filesIds);
        return order;
    }

    public OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setPurchaser(purchaserServiceImpl.getPurchaserById(order.getPurchaserId()));
        orderDto.setComment(order.getComment());
        orderDto.setFiles(fileServiceImpl.getFilesByIds(order.getFilesIds()));
        orderDto.setElements(elementServiceImpl.getElementsByIds(order.getElementsIds()));

        return orderDto;
    }

    public List<Order> mapToOrderList(List<OrderDto> orderDtoList) {
        List<Order> orders = orderDtoList.stream()
                .map(orderDto -> mapToOrder(orderDto))
                .collect(Collectors.toList());
        return Optional.ofNullable(orders).orElse(new ArrayList<>());
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        List<OrderDto> orders = orderList.stream()
                .map(order -> mapToOrderDto(order))
                .collect(Collectors.toList());
        return Optional.ofNullable(orders).orElse(new ArrayList<>());
    }
}

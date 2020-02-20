package com.zedapp.domain.dto;

import com.zedapp.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElementDto {
    private String name;
    private String destination;
    private OrderStatus status;
}

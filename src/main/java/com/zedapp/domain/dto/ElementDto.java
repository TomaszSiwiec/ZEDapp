package com.zedapp.domain.dto;

import com.zedapp.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElementDto implements Serializable {
    private long id;
    private String name;
    private String destination;
    private OrderStatus status;
}

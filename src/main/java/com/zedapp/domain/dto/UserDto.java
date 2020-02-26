package com.zedapp.domain.dto;

import com.zedapp.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private long id;
    private String username;
    private String password;
    private String mail;
    private String name;
    private String lastname;
    private LocalDate dateOfBirth;
    private UserStatus status;
    private List<OrderDto> orderDtos;
}

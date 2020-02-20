package com.zedapp.domain.dto;

import com.zedapp.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String mail;
    private String name;
    private String lastname;
    private LocalDate dateOfBirth;
    private UserStatus status;
}

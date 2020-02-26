package com.zedapp.mapper;

import com.zedapp.domain.User;
import com.zedapp.domain.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private OrderMapper orderMapper;

    public UserDto mapToDto(User user) {
        if (user == null)
            return null;
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getMail(),
                user.getName(),
                user.getLastname(),
                user.getDateOfBirth(),
                user.getStatus(),
                orderMapper.mapToDtoList(user.getOrders())
        );
    }

    public User mapToEntity(UserDto userDto) {
        if (userDto == null)
            return null;
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getMail(),
                userDto.getName(),
                userDto.getLastname(),
                userDto.getDateOfBirth(),
                userDto.getStatus(),
                orderMapper.mapToEntityList(userDto.getOrderDtos())
        );
    }

    public List<UserDto> mapToDtoList(List<User> userList) {
        if (userList.isEmpty())
            return new ArrayList<>();
        if (userList == null)
            return null;

        return userList.stream()
                .map(user -> mapToDto(user))
                .collect(Collectors.toList());
    }

    public List<User> mapToEntityList(List<UserDto> userDtoList) {
        if (userDtoList.isEmpty())
            return new ArrayList<>();
        if (userDtoList == null)
            return null;

        return userDtoList.stream()
                .map(userDto -> mapToEntity(userDto))
                .collect(Collectors.toList());
    }
}

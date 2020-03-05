package com.zedapp.service;

import com.zedapp.domain.User;
import com.zedapp.domain.dto.UserDto;
import com.zedapp.mapper.UserMapper;
import com.zedapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getAll() {
        log.info("[ZEDAPP] Returned all object from entity ORDERS");
        return userMapper.mapToDtoList(userRepository.findAll());
    }

    public UserDto get(long id) {
        User user = userRepository.findOrThrow(id);
        log.info("[ZEDAPP] Returned object with ID: " + id + " from entity ORDERS");
        return userMapper.mapToDto(user);
    }

    public UserDto create(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setMail(userDto.getMail());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setStatus(userDto.getStatus());
        log.info("[ZEDAPP] Added new object with name: " + user.getName() + " " + user.getLastname() + " to entity ORDERS");
        return userMapper.mapToDto(userRepository.save(user));
    }

    public UserDto update(long id, UserDto userDto) {
        User user = userRepository.findOrThrow(id);
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setMail(userDto.getMail());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setStatus(userDto.getStatus());
        log.info("[ZEDAPP] Updated object with ID: " + id +  " from entity ORDERS");
        return userMapper.mapToDto(userRepository.save(user));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
        log.info("[ZEDAPP] Deleted object with ID: " + id + " from entity ORDERS");
    }
}

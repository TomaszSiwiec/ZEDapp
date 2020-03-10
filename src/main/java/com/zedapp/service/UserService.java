package com.zedapp.service;

import com.zedapp.domain.Order;
import com.zedapp.domain.User;
import com.zedapp.domain.UserStatus;
import com.zedapp.domain.dto.UserDto;
import com.zedapp.mapper.UserMapper;
import com.zedapp.repository.OrderRepository;
import com.zedapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getAll() {
        log.info("[ZEDAPP] Returned all object from entity USERS");
        return userMapper.mapToDtoList(userRepository.findAll());
    }

    public UserDto get(long id) {
        User user = userRepository.findOrThrow(id);
        log.info("[ZEDAPP] Returned object with ID: " + id + " from entity USERS");
        return userMapper.mapToDto(user);
    }

    public UserDto getByOrderId(Long orderId) {
        User user = orderRepository.findOrThrow(orderId).getAddedBy();
        log.info("[ZEDAPP] Returned object with ID: " + user.getId() + " from entity ORDERS who is related to order with ID: " + orderId);
        return userMapper.mapToDto(user);
    }

    public UserDto create(UserDto userDto) {
        if (checkIfUserExistWithEmail(userDto.getMail())) {
            log.error("[ZEDAPP] User with email: " + userDto.getMail() + " exists! Enter other email address!");
            throw new IllegalStateException("[ZEDAPP] User with email = " + userDto.getMail() + " exists!");
        } else {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setMail(userDto.getMail());
            user.setName(userDto.getName());
            user.setLastname(userDto.getLastname());
            user.setDateOfBirth(userDto.getDateOfBirth());
            user.setStatus(userDto.getStatus());
            log.info("[ZEDAPP] Added new object with name: " + user.getName() + " " + user.getLastname() + " to entity USERS");
            return userMapper.mapToDto(userRepository.save(user));
        }
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
        log.info("[ZEDAPP] Updated object with ID: " + id +  " from entity USERS");
        return userMapper.mapToDto(userRepository.save(user));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
        log.info("[ZEDAPP] Deleted object with ID: " + id + " from entity USERS");
    }

    public List<UserDto> getAllOnline() {
        List<User> users = userRepository.findAll();
        List<User> onlineUsers = users.stream()
                .filter(user -> user.getStatus().equals(UserStatus.ONLINE))
                .collect(Collectors.toList());
        log.info("[ZEDAPP] Returned online users with IDs: " + onlineUsers.toArray() + " from entity USERS");
        return userMapper.mapToDtoList(onlineUsers);
    }

    public List<UserDto> getAllOffline() {
        List<User> users = userRepository.findAll();
        List<User> offlineUsers = users.stream()
                .filter(user -> user.getStatus().equals(UserStatus.OFFLINE))
                .collect(Collectors.toList());
        log.info("[ZEDAPP] Returned offline users with IDs: " + offlineUsers.toArray());
        return userMapper.mapToDtoList(offlineUsers);
    }

    public UserDto getByEmail(String email) {
        log.info("[ZEDAPP] Returned user object with email: " + email);
        return userMapper.mapToDto(findUserByEmail(email));
    }

    private boolean checkIfUserExistWithEmail(String email) {
        if (findUserByEmail(email) != null) {
            return true;
        } else {
            return false;
        }
    }

    private User findUserByEmail(String email) {
        User foundUser = userRepository.findAll().stream()
                .filter(user -> email.equals(user.getMail()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ZEDAPP] User with email: " + email + " does not exist!"));
        return foundUser;
    }
}

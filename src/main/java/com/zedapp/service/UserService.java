package com.zedapp.service;

import com.zedapp.domain.Order;
import com.zedapp.domain.User;
import com.zedapp.domain.UserStatus;
import com.zedapp.domain.dto.UserDto;
import com.zedapp.mapper.UserMapper;
import com.zedapp.repository.OrderRepository;
import com.zedapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getAll() {
        return userMapper.mapToDtoList(userRepository.findAll());
    }

    public UserDto get(long id) {
        User user = userRepository.findOrThrow(id);
        return userMapper.mapToDto(user);
    }

    public UserDto getByOrderId(Long orderId) {
        User user = orderRepository.findOrThrow(orderId).getAddedBy();
        return userMapper.mapToDto(user);
    }

    public UserDto create(UserDto userDto) {
        if (checkIfUserExistWithEmail(userDto.getMail())) {
            throw new IllegalStateException("User with email = " + userDto.getMail() + " exists!");
        } else {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setMail(userDto.getMail());
            user.setName(userDto.getName());
            user.setLastname(userDto.getLastname());
            user.setDateOfBirth(userDto.getDateOfBirth());
            user.setStatus(userDto.getStatus());
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
        return userMapper.mapToDto(userRepository.save(user));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> getAllOnline() {
        List<User> users = userRepository.findAll();
        List<User> onlineUsers = users.stream()
                .filter(user -> user.getStatus().equals(UserStatus.ONLINE))
                .collect(Collectors.toList());
        return userMapper.mapToDtoList(onlineUsers);
    }

    public List<UserDto> getAllOffline() {
        List<User> users = userRepository.findAll();
        List<User> onlineUsers = users.stream()
                .filter(user -> user.getStatus().equals(UserStatus.OFFLINE))
                .collect(Collectors.toList());
        return userMapper.mapToDtoList(onlineUsers);
    }

    public UserDto getByEmail(String email) {
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
                .orElse(null);
        return foundUser;
    }
}

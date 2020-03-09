package com.zedapp.controller;

import com.zedapp.domain.dto.UserDto;
import com.zedapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/zedapp/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/getAllOnline")
    public List<UserDto> getAllOnline() {
        return userService.getAllOnline();
    }

    @GetMapping("/getAllOffline")
    public List<UserDto> getAllOffline() {
        return userService.getAllOffline();
    }

    @GetMapping("/getByEmail")
    public UserDto getByEmail(@RequestParam(value = "email") String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("/get")
    public UserDto getById(@RequestParam(value = "id") Long id) {
        return userService.get(id);
    }

    @GetMapping("/getByOrderId/{orderId}")
    public UserDto getByOrderId(@PathVariable(name = "orderId") Long orderId) {
        return userService.getByOrderId(orderId);
    }

    @PostMapping("/create")
    public UserDto create(@RequestBody UserDto userDto) throws Exception {
        return userService.create(userDto);
    }

    @PutMapping("/update")
    public UserDto update(@RequestParam(value = "id") Long id, @RequestBody UserDto userDto) {
        return userService.update(id, userDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "id") Long id) {
        userService.delete(id);
    }
}

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

    @GetMapping("/get")
    public UserDto getById(@RequestParam(value = "id") Long id) {
        return userService.get(id);
    }

    @PostMapping("/create")
    public UserDto create(@RequestBody UserDto userDto) {
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

package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.UserDto;
import com.crud.scrobbler_backend.mapper.UsersMapper;
import com.crud.scrobbler_backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UsersService service;
    @Autowired
    private UsersMapper mapper;

    @GetMapping(value = "/user/{userId}")
    public UserDto getUser(@PathVariable long userId) throws Exception {
        return mapper.mapToUserDto(service.getUser(userId));
    }

    @PostMapping(value = "/user")
    public void createUser(@RequestBody UserDto userDto) {
        service.saveUser(mapper.mapToUser(userDto));
    }

    @PutMapping(value = "/user")
    public UserDto updateUser(@RequestBody UserDto userDto) throws Exception {
        return mapper.mapToUserDto(service.changeUser(mapper.mapToUser(userDto)));
    }

    @DeleteMapping(value = "/user/{userId}")
    public void deleteUser(@PathVariable long userId) throws Exception {
        service.deleteUser(userId);
    }
}

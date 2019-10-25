package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.UserDto;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import com.crud.scrobbler_backend.mapper.UserMapper;
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
    private UserMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public UserDto getUser(@PathVariable long userId) throws UserNotFoundException {
        return mapper.mapToUserDto(service.getUser(userId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public void createUser(@RequestBody UserDto userDto) {
        service.saveUser(mapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public UserDto updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        return mapper.mapToUserDto(service.changeUser(mapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{userId}")
    public void deleteUser(@PathVariable long userId) throws UserNotFoundException {
        service.deleteUser(userId);
    }
}

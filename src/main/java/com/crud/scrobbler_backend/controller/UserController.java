package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.domain.UserDto;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class UserController {

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public User getUser(@PathVariable long userId) throws UserNotFoundException {
        return new User();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public void createUser(@RequestBody UserDto userDto) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return new UserDto();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{userId}")
    public void deleteUser(@PathVariable long userId) throws UserNotFoundException {

    }
}

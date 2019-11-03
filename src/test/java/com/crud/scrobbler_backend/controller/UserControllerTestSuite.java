package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.domain.UserDto;
import com.crud.scrobbler_backend.mapper.UsersMapper;
import com.crud.scrobbler_backend.services.UsersService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsersService service;
    @MockBean
    private UsersMapper mapper;

    @Test
    void shouldGetUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(123L, "Test username", "Test email", "Test id", new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        long id = userDto.getId();

        when(mapper.mapToUserDto(service.getUser(id))).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/v1/user/123").contentType(MediaType.APPLICATION_JSON)
                .param("userId", Long.toString(id))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("Test username")))
                .andExpect(jsonPath("$.email", is("Test email")))
                .andExpect(jsonPath("$.spotifyId", is("Test id")))
                .andExpect(jsonPath("$.userArtistId", hasSize(0)))
                .andExpect(jsonPath("$.userTrackId", hasSize(0)))
                .andExpect(jsonPath("$.comments", hasSize(0)));
    }

    @Test
    void shouldCreateUser() throws Exception {
        //Given
        User user = new User("Test username", "Test email", "Test id");
        UserDto userDto = new UserDto(123L, "Test username", "Test email", "Test id", new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        when(service.saveUser(mapper.mapToUser(ArgumentMatchers.any(UserDto.class)))).thenReturn(user);

        //When & Then
        mockMvc.perform(post("/v1/user").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(service).saveUser(mapper.mapToUser(ArgumentMatchers.any(UserDto.class)));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(123L, "Test username", "Test email", "Test id", new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        UserDto updatedUser = new UserDto(123L, "Updated username", "Updated email", "Updated id", new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        when(mapper.mapToUserDto(service.changeUser(mapper.mapToUser(userDto)))).thenReturn(updatedUser);

        //When & Then
        mockMvc.perform(put("/v1/user").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("Updated username")))
                .andExpect(jsonPath("$.email", is("Updated email")))
                .andExpect(jsonPath("$.spotifyId", is("Updated id")));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //Given
        User user = new User("Test username", "Test email", "Test id");
        UserDto userDto = new UserDto(123L, "Test username", "Test email", "Test id", new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());

        when(service.getUser(userDto.getId())).thenReturn(user);
        when(service.saveUser(mapper.mapToUser(ArgumentMatchers.any(UserDto.class)))).thenReturn(user);

        //When & Then
        mockMvc.perform(delete("/v1/user/123").contentType(MediaType.APPLICATION_JSON)
                .param("userId", Long.toString(userDto.getId()))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
        verify(service).deleteUser(userDto.getId());
    }
}
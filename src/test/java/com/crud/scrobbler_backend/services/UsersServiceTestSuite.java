package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsersServiceTestSuite {
    @Autowired
    private UsersService service;

    @Test
    void shouldGetUser() throws UserNotFoundException {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        service.saveUser(user);
        long id = user.getId();

        //When
        User savedUser = service.getUser(id);

        //Then
        assertEquals("test name", savedUser.getUsername());
        assertEquals("test email", savedUser.getEmail());
        assertEquals("test spotify id", savedUser.getSpotifyId());

        //CleanUp
        service.deleteUser(id);
    }

    @Test
    void shouldGetUserBySpotifyId() throws UserNotFoundException {
        //Given
        User user = new User("test name", "test email", "abcd");
        service.saveUser(user);
        String spotifyId = user.getSpotifyId();
        long id = user.getId();

        //When
        User savedUser = service.getUserBySpotifyId(spotifyId);

        //Then
        assertEquals("test name", savedUser.getUsername());
        assertEquals("test email", savedUser.getEmail());
        assertEquals("abcd", savedUser.getSpotifyId());

        //CleanUp
        service.deleteUser(id);
    }

    @Test
    void shouldSaveUser() throws UserNotFoundException {
        //Given
        User user = new User("test name", "test email", "test spotify id");

        //When
        User savedUser = service.saveUser(user);
        long id = savedUser.getId();

        //Then
        assertEquals("test name", savedUser.getUsername());
        assertEquals("test email", savedUser.getEmail());
        assertEquals("test spotify id", savedUser.getSpotifyId());

        //CleanUp
        service.deleteUser(id);
    }

    @Test
    void shoudldChangeUser() throws UserNotFoundException {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        service.saveUser(user);
        long id = user.getId();

        //When
        User updateUser = service.getUser(id);
        updateUser.setEmail("test email 2");
        updateUser.setUsername("test name 2");
        updateUser.setSpotifyId("test spotify id 2");

        //Then
        assertEquals("test name 2", updateUser.getUsername());
        assertEquals("test email 2", updateUser.getEmail());
        assertEquals("test spotify id 2", updateUser.getSpotifyId());

        //CleanUp
        service.deleteUser(id);
    }
}
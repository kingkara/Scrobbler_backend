package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.domain.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsersMapperTestSuite {
    @Autowired
    private UsersMapper mapper;

    @Test
    void shouldMapToUser() {
        //Given
        UserDto userDto = new UserDto( "Test username", "Test email", "Test spotify id");

        //When
        User user = mapper.mapToUser(userDto);

        //Then
        assertEquals("Test username", user.getUsername());
        assertEquals("Test email", user.getEmail());
        assertEquals("Test spotify id", user.getSpotifyId());
        assertEquals(0, user.getUsersArtists().size());
        assertEquals(0, user.getComments().size());
        assertEquals(0, user.getUsersTracks().size());
    }

    @Test
    void shouldMapToUserDto() {
        //Given
        User user = new User("Test username", "Test email", "Test spotify id");

        //When
        UserDto userDto = mapper.mapToUserDto(user);

        //Then
        assertEquals("Test username", userDto.getUsername());
        assertEquals("Test email", userDto.getEmail());
        assertEquals("Test spotify id", userDto.getSpotifyId());
    }
}
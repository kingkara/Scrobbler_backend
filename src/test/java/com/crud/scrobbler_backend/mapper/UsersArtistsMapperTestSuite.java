package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.domain.UsersArtist;
import com.crud.scrobbler_backend.domain.UsersArtistDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsersArtistsMapperTestSuite {
    @Autowired
    private UsersArtistsMapper mapper;

    @Test
    void shouldMapToUsersArtistDtoList() {
        //Given
        List<UsersArtist> usersArtists = new ArrayList<>();
        UsersArtist usersArtist = new UsersArtist(new User("Test user", "Test mail", "Test spotify id"),
                new Artist("Test artist", "Test id"));
        UsersArtist usersArtist1 = new UsersArtist(new User("Test user 2", "Test mail 2", "Test spotify id 2"),
                new Artist("Test artist 2", "Test id 2"));
        usersArtists.add(usersArtist);
        usersArtists.add(usersArtist1);

        //When
        List<UsersArtistDto> usersArtistDtos = mapper.mapToUsersArtistDtoList(usersArtists);

        //Then
        assertEquals(2, usersArtistDtos.size());
        assertEquals("Test user", usersArtistDtos.get(0).getUser().getUsername());
        assertEquals("Test mail", usersArtistDtos.get(0).getUser().getEmail());
        assertEquals("Test spotify id", usersArtistDtos.get(0).getUser().getSpotifyId());
        assertEquals("Test artist", usersArtistDtos.get(0).getArtist().getName());
        assertEquals("Test id", usersArtistDtos.get(0).getArtist().getSpotifyArtistId());
        assertEquals("Test user 2", usersArtistDtos.get(1).getUser().getUsername());
        assertEquals("Test mail 2", usersArtistDtos.get(1).getUser().getEmail());
        assertEquals("Test spotify id 2", usersArtistDtos.get(1).getUser().getSpotifyId());
        assertEquals("Test artist 2", usersArtistDtos.get(1).getArtist().getName());
        assertEquals("Test id 2", usersArtistDtos.get(1).getArtist().getSpotifyArtistId());
    }
}
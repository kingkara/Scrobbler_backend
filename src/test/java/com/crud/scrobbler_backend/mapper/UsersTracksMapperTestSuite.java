package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.*;
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
class UsersTracksMapperTestSuite {
    @Autowired
    private UsersTracksMapper mapper;

    @Test
    void shouldMapToUsersTrackDto() {
        //Given
        UsersTrack usersTrack = new UsersTrack(new User("Test username", "Test email", "Test spotify id"),
                new Track("Test title", new Artist("Test artist", "Test id")));

        //When
        UsersTrackDto usersTrackDto = mapper.mapToUsersTrackDto(usersTrack);

        //Then
        assertEquals("Test username", usersTrackDto.getUser().getUsername());
        assertEquals("Test email", usersTrackDto.getUser().getEmail());
        assertEquals("Test spotify id", usersTrackDto.getUser().getSpotifyId());
        assertEquals("Test title", usersTrackDto.getTrack().getTitle());
        assertEquals("Test artist", usersTrackDto.getTrack().getArtist().getName());
        assertEquals("Test id", usersTrackDto.getTrack().getArtist().getSpotifyArtistId());
    }

    @Test
    void shouldMapToUsersTrackDtoList() {
        //Given
        List<UsersTrack> usersTracks = new ArrayList<>();
        UsersTrack usersTrack = new UsersTrack(new User("Test username", "Test email", "Test spotify id"),
                new Track("Test title", new Artist("Test artist", "Test id")));
        UsersTrack usersTrack1 = new UsersTrack(new User("Test username 2", "Test email 2", "Test spotify id 2"),
                new Track("Test title 2", new Artist("Test artist 2", "Test id 2")));
        usersTracks.add(usersTrack);
        usersTracks.add(usersTrack1);

        //When
        List<UsersTrackDto> usersTrackDtos = mapper.mapToUsersTrackDtoList(usersTracks);

        //Then
        assertEquals(2, usersTrackDtos.size());
        assertEquals("Test username", usersTrackDtos.get(0).getUser().getUsername());
        assertEquals("Test email", usersTrackDtos.get(0).getUser().getEmail());
        assertEquals("Test spotify id", usersTrackDtos.get(0).getUser().getSpotifyId());
        assertEquals("Test title", usersTrackDtos.get(0).getTrack().getTitle());
        assertEquals("Test artist", usersTrackDtos.get(0).getTrack().getArtist().getName());
        assertEquals("Test id", usersTrackDtos.get(0).getTrack().getArtist().getSpotifyArtistId());
        assertEquals("Test username 2", usersTrackDtos.get(1).getUser().getUsername());
        assertEquals("Test email 2", usersTrackDtos.get(1).getUser().getEmail());
        assertEquals("Test spotify id 2", usersTrackDtos.get(1).getUser().getSpotifyId());
        assertEquals("Test title 2", usersTrackDtos.get(1).getTrack().getTitle());
        assertEquals("Test artist 2", usersTrackDtos.get(1).getTrack().getArtist().getName());
        assertEquals("Test id 2", usersTrackDtos.get(1).getTrack().getArtist().getSpotifyArtistId());
    }
}
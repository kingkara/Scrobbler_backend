package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArtistMapperTestSuite {
    @Autowired
    private ArtistMapper artistMapper;

    @Test
    void shouldMapToArtist() {
        //Given
        ArtistDto artistDto = new ArtistDto("Test name", "Test id");

        //When
        Artist artist = artistMapper.mapToArtist(artistDto);

        //Then
        assertEquals("Test name", artist.getName());
        assertEquals("Test id", artist.getSpotifyArtistId());
        assertEquals(0, artist.getTracks().size());
        assertEquals(0, artist.getUsersArtist().size());
    }

    @Test
    void shouldMapToArtistDto() {
        //Given
        Artist artist = new Artist("Test name", "Test id");

        //When
        ArtistDto artistDto = artistMapper.mapToArtistDto(artist);

        //Then
        assertEquals("Test name", artistDto.getName());
        assertEquals("Test id", artistDto.getSpotifyArtistId());
        assertEquals(0, artistDto.getTracks().size());
        assertEquals(0, artistDto.getUsersArtists().size());
    }

    @Test
    void shouldMapToArtistDtoList() {
        //Given
        List<Artist> list = new ArrayList<>();
        Artist artist = new Artist("Test name", "Test id");
        Artist artist1 = new Artist("Test name 2", "Test id 2");
        list.add(artist);
        list.add(artist1);

        //When
        List<ArtistDto> artistDtoList = artistMapper.mapToArtistDtoList(list);

        //Then
        assertEquals(2, artistDtoList.size());
        assertEquals("Test name", artistDtoList.get(0).getName());
        assertEquals("Test id", artistDtoList.get(0).getSpotifyArtistId());
        assertEquals("Test name 2", artistDtoList.get(1).getName());
        assertEquals("Test id 2", artistDtoList.get(1).getSpotifyArtistId());
    }
}
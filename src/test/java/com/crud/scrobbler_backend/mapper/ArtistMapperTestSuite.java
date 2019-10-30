package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.ArtistDto;
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
class ArtistMapperTestSuite {

    @Autowired
    private ArtistMapper artistMapper;

    @Test
    void shouldMapToArtist() {
        //Given
        ArtistDto artistDto = new ArtistDto(1, "Korn", new ArrayList<>(), new ArrayList<>());

        //When
        Artist artist = artistMapper.mapToArtist(artistDto);

        //Then
        assertEquals("Korn", artist.getName());
        assertEquals(0, artist.getTracks().size());
        assertEquals(0, artist.getUsersArtist().size());
    }

    @Test
    void shouldMapToArtistDto() {
        //Given
        Artist artist = new Artist("Korn");

        //When
        ArtistDto artistDto = artistMapper.mapToArtistDto(artist);

        //Then
        assertEquals("Korn", artistDto.getName());
        assertEquals(0, artistDto.getTracks().size());
        assertEquals(0, artistDto.getUsersArtists().size());
    }

    @Test
    void shouldMapToArtistDtoList() {
        //Given
        List<Artist> list = new ArrayList<>();
        Artist artist = new Artist("Korn");
        Artist artist1 = new Artist("Fleedwood Mac");
        list.add(artist);
        list.add(artist1);

        //When
        List<ArtistDto> artistDtoList = artistMapper.mapToArtistDtoList(list);

        //Then
        assertEquals(2, artistDtoList.size());
        assertEquals("Korn", artistDtoList.get(0).getName());
        assertEquals("Fleedwood Mac", artistDtoList.get(1).getName());
    }
}
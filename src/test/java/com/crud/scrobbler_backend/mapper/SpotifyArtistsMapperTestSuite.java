package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.spotify.SpotifyArtist;
import com.crud.scrobbler_backend.domain.spotify.SpotifyArtistDto;
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
class SpotifyArtistsMapperTestSuite {
    @Autowired
    private SpotifyArtistsMapper mapper;

    @Test
    void shouldMapToSpotifyArtistList() {
        //Given
        List<SpotifyArtistDto> spotifyArtistDtos = new ArrayList<>();
        SpotifyArtistDto spotifyArtistDto = new SpotifyArtistDto("Test id", "Test artist");
        SpotifyArtistDto spotifyArtistDto1 = new SpotifyArtistDto("Test id 2", "Test artist 2");
        spotifyArtistDtos.add(spotifyArtistDto);
        spotifyArtistDtos.add(spotifyArtistDto1);

        //When
        List<SpotifyArtist> spotifyArtists = mapper.mapToSpotifyArtistList(spotifyArtistDtos);

        //Then
        assertEquals(2, spotifyArtists.size());
        assertEquals("Test id", spotifyArtists.get(0).getId());
        assertEquals("Test artist", spotifyArtists.get(0).getName());
        assertEquals("Test id 2", spotifyArtists.get(1).getId());
        assertEquals("Test artist 2", spotifyArtists.get(1).getName());
    }
}
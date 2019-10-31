package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrack;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrackDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyTrackDto;
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
class SpotifyFullTracksMapperTestSuite {
    @Autowired
    private SpotifyFullTracksMapper mapper;

    @Test
    void shouldMapToSpotifyTrackList() {
        //Given
        List<SpotifyFullTrackDto> spotifyTrackDtos = new ArrayList<>();
        SpotifyFullTrackDto spotifyTrackDto = new SpotifyFullTrackDto(new SpotifyTrackDto(
                new ArrayList<>(), "Test id", "Test title"), "Test date");
        SpotifyFullTrackDto spotifyTrackDto1 = new SpotifyFullTrackDto(new SpotifyTrackDto(
                new ArrayList<>(), "Test id 2", "Test title 2"), "Test date 2");
        spotifyTrackDtos.add(spotifyTrackDto);
        spotifyTrackDtos.add(spotifyTrackDto1);

        //When
        List<SpotifyFullTrack> spotifyFullTracks = mapper.mapToSpotifyTrackList(spotifyTrackDtos);

        //Then
        assertEquals(2, spotifyFullTracks.size());
        assertEquals("Test id", spotifyFullTracks.get(0).getSpotifyTrackDto().getId());
        assertEquals("Test title", spotifyFullTracks.get(0).getSpotifyTrackDto().getTitle());
        assertEquals("Test date", spotifyFullTracks.get(0).getPlayedAt());
        assertEquals("Test id 2", spotifyFullTracks.get(1).getSpotifyTrackDto().getId());
        assertEquals("Test title 2", spotifyFullTracks.get(1).getSpotifyTrackDto().getTitle());
        assertEquals("Test date 2", spotifyFullTracks.get(1).getPlayedAt());
    }
}
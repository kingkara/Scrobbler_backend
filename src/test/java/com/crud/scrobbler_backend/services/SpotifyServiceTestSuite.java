package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.spotify.SpotifyCurrentlyPlayedDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrackDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyTrackDto;
import com.crud.scrobbler_backend.spotify.client.SpotifyClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SpotifyServiceTestSuite {
    @InjectMocks
    private SpotifyService spotifyService;

    @Mock
    private SpotifyClient client;

    @Test
    void shouldGetPlayback() throws JsonProcessingException {
        //Given
        List<SpotifyFullTrackDto> testList = new ArrayList<>();
        SpotifyFullTrackDto spotifyFullTrackDto = new SpotifyFullTrackDto(new SpotifyTrackDto(
                new ArrayList<>(), "test id", "test title"), "test time");
        SpotifyFullTrackDto spotifyFullTrackDto1 = new SpotifyFullTrackDto(new SpotifyTrackDto(
                new ArrayList<>(), "test id 2", "test title 2"), "test time 2");
        testList.add(spotifyFullTrackDto);
        testList.add(spotifyFullTrackDto1);
        when(client.getRecentlyPlayed()).thenReturn(testList);
        when(spotifyService.getPlayback()).thenReturn(testList);

        //When
        List<SpotifyFullTrackDto> recentlyPlayed = spotifyService.getPlayback();

        //Then
        assertEquals("test id", recentlyPlayed.get(0).getSpotifyTrackDto().getId());
        assertEquals("test title", recentlyPlayed.get(0).getSpotifyTrackDto().getTitle());
        assertEquals("test time", recentlyPlayed.get(0).getPlayedAt());
        assertEquals("test id 2", recentlyPlayed.get(1).getSpotifyTrackDto().getId());
        assertEquals("test title 2", recentlyPlayed.get(1).getSpotifyTrackDto().getTitle());
        assertEquals("test time 2", recentlyPlayed.get(1).getPlayedAt());
        assertEquals(2, recentlyPlayed.size());
    }

    @Test
    void shouldGetCurrentPlaying() throws JsonProcessingException {
        //Given
        SpotifyCurrentlyPlayedDto currentlyPlayedDto = new SpotifyCurrentlyPlayedDto("test id", "test title", new ArrayList<>());
        when(client.getCurrentPlayedTrack()).thenReturn(currentlyPlayedDto);
        when(spotifyService.getCurrentPlaying()).thenReturn(currentlyPlayedDto);

        //When
        SpotifyCurrentlyPlayedDto currentlyPlayedTrack = spotifyService.getCurrentPlaying();

        //Then
        assertEquals("test id", currentlyPlayedTrack.getId());
        assertEquals("test title", currentlyPlayedTrack.getTitle());
        assertEquals(0, currentlyPlayedTrack.getArtistDtos().size());
    }
}
package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.LyricsDto;
import com.crud.scrobbler_backend.lyrics.client.LyricsClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class LyricsApiServiceTestSuite {
    @InjectMocks
    private LyricsApiService service;

    @Mock
    private LyricsClient client;

    @Test
    void shouldGetLyrics() {
        //Given
        String artistName = "Test name";
        String title = "Test title";
        LyricsDto lyrics = new LyricsDto("Test lyrics");
        when(client.getTrackLyrics(artistName,title)).thenReturn(lyrics);
        when(service.getLyrics(artistName,title)).thenReturn(lyrics);

        //When
        LyricsDto lyricsFromApi = service.getLyrics(artistName, title);

        //Then
        assertEquals("Test lyrics", lyricsFromApi.getLyrics());
    }
}
package com.crud.scrobbler_backend.lyrics.client;

import com.crud.scrobbler_backend.domain.lyricsApi.LyricsDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class LyricsClientTestSuite {
    @InjectMocks
    private LyricsClient client;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private LyricsConfig config;

    @Test
    void shouldGetTrackLyrics() throws URISyntaxException {
        //Given
        LyricsDto lyricsDto = new LyricsDto("test lyrics");

        URI uri = new URI("http://test.com/testArtist/testTitle?apikey=test");

        when(config.getLyricsApiEndpoint()).thenReturn("http://test.com/");
        when(config.getLyricsToken()).thenReturn("test");
        when(restTemplate.getForObject(uri, LyricsDto.class)).thenReturn(lyricsDto);

        //When
        LyricsDto lyricsDtoFromRequest = client.getTrackLyrics("testArtist", "testTitle");

        //Then
        assertEquals("test lyrics", lyricsDtoFromRequest.getLyrics());
    }
}
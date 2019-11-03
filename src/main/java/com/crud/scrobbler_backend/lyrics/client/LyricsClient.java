package com.crud.scrobbler_backend.lyrics.client;

import com.crud.scrobbler_backend.domain.LyricsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class LyricsClient {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LyricsConfig config;

    public LyricsDto getTrackLyrics(String artistName, String title) {
        URI uri = UriComponentsBuilder.fromHttpUrl(config.getLyricsApiEndpoint() + artistName + "/" + title)
                .queryParam("apikey", config.getLyricsToken())
                .build().encode().toUri();

        LyricsDto lyricsResponse = restTemplate.getForObject(uri, LyricsDto.class);
        if (lyricsResponse != null) {
            return lyricsResponse;
        }
        return new LyricsDto();
    }
}

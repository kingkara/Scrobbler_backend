package com.crud.scrobbler_backend.lyrics.client;

import com.crud.scrobbler_backend.domain.LyricsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class LyricsClient {
    @Value("${lyrics.api.endpoint.prod}")
    private String lyricsApiEndpoint;

    @Value("$lyrics.api.token")
    private String lyricsToken;

    @Autowired
    private RestTemplate restTemplate;

    public LyricsDto getTrackLyrics(String artistName, String title) {
        URI uri = UriComponentsBuilder.fromHttpUrl(lyricsApiEndpoint + artistName + "/" + title)
                .queryParam("apikey", lyricsApiEndpoint)
                .build().encode().toUri();

        LyricsDto lyricsResponse = restTemplate.getForObject(uri, LyricsDto.class);
        if (lyricsResponse != null) {
            return lyricsResponse;
        }
        return new LyricsDto();
    }
}

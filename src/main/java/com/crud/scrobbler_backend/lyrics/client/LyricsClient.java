package com.crud.scrobbler_backend.lyrics.client;

import com.crud.scrobbler_backend.domain.lyricsApi.LyricsDto;
import com.crud.scrobbler_backend.domain.lyricsApi.ResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class LyricsClient {
    @Value("${lyrics.api.endpoint.prod}")
    private String lyricsApiEndpoint;
    @Autowired
    private RestTemplate restTemplate;

    private static String lyricsToken = "CNtQ9WrnayNhkJ0IaSMSmNqVGN9DWGxFxM5dkLWBg6BjRn6rgW2aatQ98OQdUrkA";

    public LyricsDto getTrackLyrics(String artistName, String title) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                lyricsApiEndpoint + artistName + "/" + title + "?apikey=" + lyricsToken, HttpMethod.GET, entity, String.class);

        String responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        ResultDto lyricsResponse = mapper.readValue(Objects.requireNonNull(responseBody), ResultDto.class);
        if (lyricsResponse != null) {
            return lyricsResponse.getLyricsTrackDto().getTrackWithLyrics();
        }
        return new LyricsDto();
    }
}

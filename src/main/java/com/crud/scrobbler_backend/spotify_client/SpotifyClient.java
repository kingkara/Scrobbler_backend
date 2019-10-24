package com.crud.scrobbler_backend.spotify_client;

import com.crud.scrobbler_backend.domain.SpotifyTrackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SpotifyClient {
    @Value("${spotify.api.endpoint.prod}")
    private String spotifyApiEndpoint;

    @Value("$spotify.app.token")
    private String spotifyToken;

    @Value("$spotify.app.user-id")
    private String spotifyUserId;

    @Autowired
    private RestTemplate restTemplate;

    public List<SpotifyTrackDto> getRecentlyPlayed() {

        URI uri = UriComponentsBuilder.fromHttpUrl(spotifyApiEndpoint + "/me/player/recently_played")
                .queryParam("access_token", spotifyToken)
                .build().encode().toUri();

        SpotifyTrackDto[] tracksResponse = restTemplate.getForObject(uri, SpotifyTrackDto[].class);
        if(tracksResponse!=null) {
            return Arrays.asList(tracksResponse);
        }
        return new ArrayList<>();
    }

    public SpotifyTrackDto getCurrentPlayedTrack() {
        URI uri = UriComponentsBuilder.fromHttpUrl(spotifyApiEndpoint + "me/player/currently-playing")
                .queryParam("access_token", spotifyToken)
                .build().encode().toUri();

        SpotifyTrackDto trackResponse = restTemplate.getForObject(uri, SpotifyTrackDto.class);
        if(trackResponse!=null) {
            return trackResponse;
        }
        return new SpotifyTrackDto();
    }
}

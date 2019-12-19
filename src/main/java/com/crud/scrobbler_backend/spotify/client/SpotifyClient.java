package com.crud.scrobbler_backend.spotify.client;

import com.crud.scrobbler_backend.domain.spotify.SpotifyCurrentlyPlayedDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrackDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyItemDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyItemsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.crud.scrobbler_backend.spotify.client.SpotifyAuthorize.getTokenFromSpotifyServer;

@Component
public class SpotifyClient {
    @Value("${spotify.api.endpoint.prod}")
    private String spotifyApiEndpoint;
    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotifyClient.class);

    private String spotifyToken = getTokenFromSpotifyServer();

    public List<SpotifyFullTrackDto> getRecentlyPlayed() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + spotifyToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    spotifyApiEndpoint + "recently-played?type=track&limit=50", HttpMethod.GET, entity, String.class);

            String tracksResponse = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            SpotifyItemsDto objects = mapper.readValue(Objects.requireNonNull(tracksResponse), SpotifyItemsDto.class);
            return objects.getSpotifyTrackDtos();
        } catch (HttpClientErrorException | JsonProcessingException e) {
            LOGGER.warn("Could not get your playback from Spotify");
        }
        return new ArrayList<>();
    }

    public SpotifyCurrentlyPlayedDto getCurrentPlayedTrack() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + spotifyToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    spotifyApiEndpoint + "currently-playing", HttpMethod.GET, entity, String.class);
            String tracksResponse = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            SpotifyItemDto object = mapper.readValue(Objects.requireNonNull(tracksResponse), SpotifyItemDto.class);
            return object.getSpotifyCurrentlyPlayed();
        } catch (HttpClientErrorException | NullPointerException | JsonProcessingException e) {
            LOGGER.warn("Could not get currently played track from Spotify.");
        }
        return new SpotifyCurrentlyPlayedDto();
    }
}

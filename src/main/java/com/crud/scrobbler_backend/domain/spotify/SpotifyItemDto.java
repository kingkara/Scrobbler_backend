package com.crud.scrobbler_backend.domain.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyItemDto {
    @JsonProperty ("item")
    private SpotifyCurrentlyPlayedDto spotifyCurrentlyPlayed;

}

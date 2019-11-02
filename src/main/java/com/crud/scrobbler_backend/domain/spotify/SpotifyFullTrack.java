package com.crud.scrobbler_backend.domain.spotify;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpotifyFullTrack {
    private SpotifyTrackDto spotifyTrackDto;
    private String playedAt;
}

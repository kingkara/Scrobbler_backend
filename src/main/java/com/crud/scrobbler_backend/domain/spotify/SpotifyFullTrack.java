package com.crud.scrobbler_backend.domain.spotify;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class SpotifyFullTrack {
    private SpotifyTrackDto spotifyTrackDto;
    private String playedAt;
}

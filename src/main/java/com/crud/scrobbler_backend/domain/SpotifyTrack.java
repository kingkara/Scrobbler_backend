package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class SpotifyTrack {
    private String id;
    private String name;
    private SpotifyArtist artist;
    private Instant playedAt;
}

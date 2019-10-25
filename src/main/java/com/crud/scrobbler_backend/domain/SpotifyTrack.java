package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpotifyTrack {
    private String id;
    private String name;
    private SpotifyArtist artist;
}
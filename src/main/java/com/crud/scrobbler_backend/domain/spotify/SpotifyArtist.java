package com.crud.scrobbler_backend.domain.spotify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpotifyArtist {
    private String id;
    private String name;
}

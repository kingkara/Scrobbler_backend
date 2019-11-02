package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {
    private long id;
    private String name;
    private String spotifyArtistId;
    private List<UsersArtist> usersArtists;
    private List<Track> tracks;

    public ArtistDto(String name, String spotifyArtistId) {
        this.name = name;
        this.spotifyArtistId = spotifyArtistId;
    }
}

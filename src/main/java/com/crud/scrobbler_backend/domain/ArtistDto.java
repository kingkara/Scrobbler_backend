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
    private long count;
    private long spotifyId;
    private List<User> users;
    private List<Track> tracks;
}
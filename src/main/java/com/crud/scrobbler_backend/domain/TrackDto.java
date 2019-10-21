package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TrackDto {
    private long id;
    private String title;
    private long count;
    private Artist artist;
    private SpotifyTrack spotifyTrack;
    private List<User> users;
}

package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ArtistDto {
    private long id;
    private String name;
    private long count;
    private List<User> users;
    private List<Track> tracks;
}

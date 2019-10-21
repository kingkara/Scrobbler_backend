package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String email;
    private long spotifyId;
    private List<Artist> userArtistId;
    private List<Track> userTrackId;
}

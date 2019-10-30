package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String email;
    private String spotifyId;
    private List<UsersArtist> userArtistId;
    private List<UsersTrack> userTrackId;
    private List<Comment> comments;
}

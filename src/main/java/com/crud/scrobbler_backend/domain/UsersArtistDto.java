package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersArtistDto {
    private long id;
    private long count;
    private String lastPlayedTime;
    private User user;
    private Artist artist;
}

package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersArtistDto {
    private UsersArtist.UsersArtistIdBuilder id;
    private long count;
    private String lastPlayedTime;
    private User user;
    private Artist artist;
    private String artistName;

    public UsersArtistDto(User user, Artist artist) {
        this.user = user;
        this.artist = artist;
        this.artistName = artist.getName();
    }
}

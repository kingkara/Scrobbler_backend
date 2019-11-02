package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrackDto {
    private long id;
    private String title;
    private Artist artist;
    private List<UsersTrack> usersTracks;
    private List<Comment> comment;

    public TrackDto(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }
}

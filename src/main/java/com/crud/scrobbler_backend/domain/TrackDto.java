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
    private long count;
    private Artist artist;
    private long spotifyTrack;
    private List<UsersTrack> usersTracks;
    private List<Comment> comment;
}

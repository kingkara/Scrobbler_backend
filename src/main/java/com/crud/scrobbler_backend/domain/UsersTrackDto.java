package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersTrackDto {
    private UsersTrack.UsersTrackIdBuilder id;
    private String lastPlayedTime;
    private long count;
    private boolean favouriteStatus;
    private User user;
    private Track track;

    public UsersTrackDto(User user, Track track) {
        this.user = user;
        this.track = track;
    }
}

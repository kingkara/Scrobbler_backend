package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersTrackDto {
    private long id;
    private String lastPlayedTime;
    private long count;
    private boolean favouriteStatus;
    private User user;
    private Track track;
}

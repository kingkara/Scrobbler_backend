package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersArtistDto {
    private long id;
    private Instant lastPLayedTime;
    private long count;
    private User user;
    private Artist artist;
}

package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS_ARTIST")
public class UsersArtist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USERS_ARTIST_ID", unique = true)
    private long id;

    @Column (name = "LASTLY_PLAYED_AT")
    @NotNull
    private Instant lastPLayedTime;

    @Column (name = "COUNT")
    @NotNull
    private long count;

    @ManyToOne
    @JoinColumn (name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn (name = "ARTIST_ID")
    private Artist artist;

    public UsersArtist(@NotNull Instant lastPLayedTime, @NotNull long count, User user, Artist artist) {
        this.lastPLayedTime = lastPLayedTime;
        this.count = count;
        this.user = user;
        this.artist = artist;

        user.getUsersArtists().add(this);
        artist.getUsersArtist().add(this);
    }
}

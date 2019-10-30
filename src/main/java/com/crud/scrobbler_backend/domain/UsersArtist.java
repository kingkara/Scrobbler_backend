package com.crud.scrobbler_backend.domain;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    @Column (name = "COUNT")
    @NotNull
    private long count;

    @Setter
    @Column (name = "LASTLY_PLAYED_AT")
    @NotNull
    private String lastPlayedTime;

    @ManyToOne
    @JoinColumn (name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn (name = "ARTIST_ID")
    private Artist artist;

    public UsersArtist(User user, Artist artist) {
        this.user = user;
        this.artist = artist;

        user.getUsersArtists().add(this);
        artist.getUsersArtist().add(this);
        this.count=1;
    }
}

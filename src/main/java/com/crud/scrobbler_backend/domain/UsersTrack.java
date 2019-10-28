package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS_TRACK")
public class UsersTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USERS_TRACK_ID", unique = true)
    private long id;

    @Setter
    @Column (name = "LASTLY_PLAYED_AT")
    @NotNull
    private Instant lastPlayedTime;

    @Setter
    @Column (name = "COUNT")
    @NotNull
    private long count;

    @Setter
    @Column(name = "FAVOURITE")
    private boolean favouriteStatus;

    @ManyToOne
    @JoinColumn (name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn (name = "TRACK_ID")
    private Track track;

    public UsersTrack(User user, Track track) {
        this.user = user;
        this.track = track;

        user.getUsersTracks().add(this);
        track.getUsersTracks().add(this);
        this.count=1;
    }
}

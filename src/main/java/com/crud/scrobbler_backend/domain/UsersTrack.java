package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS_TRACK")
public class UsersTrack {
    @Embeddable
    public static class UsersTrackIdBuilder implements Serializable {
        @Column(name = "USER_ID")
        protected Long userId;
        @Column(name = "TRACK_ID")
        private Long trackId;

        UsersTrackIdBuilder() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UsersTrackIdBuilder)) return false;

            UsersTrackIdBuilder id = (UsersTrackIdBuilder) o;

            if (!Objects.equals(userId, id.userId)) return false;
            return Objects.equals(trackId, id.trackId);
        }

        @Override
        public int hashCode() {
            return userId.hashCode() + trackId.hashCode();
        }
    }

    public UsersTrack(User user, Track track) {
        this.user = user;
        this.track = track;
        this.id.trackId = track.getId();
        this.id.userId = user.getId();
        this.count = 1;
        this.favouriteStatus = false;
    }

    @EmbeddedId
    @Column(name = "USERS_TRACK_ID", unique = true)
    private UsersTrackIdBuilder id = new UsersTrackIdBuilder();

    @Setter
    @Column(name = "LASTLY_PLAYED_AT")
    @NotNull
    private String lastPlayedTime;

    @Setter
    @Column(name = "COUNT")
    @NotNull
    private long count;

    @Setter
    @Column(name = "FAVOURITE")
    private boolean favouriteStatus;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "TRACK_ID", insertable = false, updatable = false)
    private Track track;
}

package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS_ARTIST")
public class UsersArtist {
    @Embeddable
    public static class UsersArtistIdBuilder implements Serializable {
        @Column(name = "USER_ID")
        protected Long userId;
        @Column(name = "ARTIST_ID")
        private Long artistId;

        UsersArtistIdBuilder() {
        }

        public UsersArtistIdBuilder(long userId, long artistId) {
            this.userId = userId;
            this.artistId = artistId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UsersArtistIdBuilder)) return false;

            UsersArtistIdBuilder id = (UsersArtistIdBuilder) o;

            if (!userId.equals(id.userId)) return false;
            return artistId.equals(id.artistId);
        }

        @Override
        public int hashCode() {
            return userId.hashCode() + artistId.hashCode();
        }
    }

    @EmbeddedId
    @Column(name = "USERS_ARTIST_ID", unique = true)
    protected UsersArtistIdBuilder id = new UsersArtistIdBuilder();

    @Setter
    @Column (name = "COUNT")
    @NotNull
    private long count;

    @Setter
    @Column (name = "LASTLY_PLAYED_AT")
    @NotNull
    private String lastPlayedTime;

    @ManyToOne
    @JoinColumn (name = "USER_ID", insertable = false, updatable = false)
    protected User user;

    @ManyToOne
    @JoinColumn (name = "ARTIST_ID", insertable = false, updatable = false)
    protected Artist artist;

    public UsersArtist(User user, Artist artist) {
        this.user = user;
        this.artist = artist;
        this.id.userId = user.getId();
        this.id.artistId = artist.getArtistId();
        this.count=1;

        user.getUsersArtists().add(this);
        artist.getUsersArtist().add(this);
    }
}

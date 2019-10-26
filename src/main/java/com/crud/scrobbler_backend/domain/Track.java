package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TRACKS")
public class Track {

    public Track(String title, long count, Artist artist, long spotifyTrack,
                 List<UsersTrack> usersTracks, List<Comment> comments) {
        this.title = title;
        this.count = count;
        this.artist = artist;
        this.spotifyTrack = spotifyTrack;
        this.usersTracks = usersTracks;
        this.comments = comments;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRACK_ID", unique = true)
    private long id;

    @Setter
    @Column(name = "TITLE")
    private String title;

    @Column (name = "COUNT_BY_ALL_USERS")
    private long count;

    @Setter
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "ARTIST_ID")
    private Artist artist;

    @Column(name = "SPOTIFY_TRACK_ID")
    private long spotifyTrack;

    @OneToMany (targetEntity = UsersTrack.class,
            mappedBy = "track",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<UsersTrack> usersTracks;

    @OneToMany (
            targetEntity = Comment.class,
            mappedBy = "track",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Comment> comments;
}

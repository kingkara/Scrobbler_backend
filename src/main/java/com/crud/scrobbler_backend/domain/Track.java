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

    public Track(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRACK_ID", unique = true)
    private long id;

    @Setter
    @Column(name = "TITLE")
    private String title;

    @Setter
    @Column(name = "FAVOURITE")
    private boolean favouriteStatus;

    @Column
    private long count;

    @Setter
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "ARTIST_ID")
    private Artist artist;

    @Column(name = "SPOTIFY_TRACK_ID")
    private long spotifyTrack;

    @ManyToMany
    @JoinTable(
            name = "JOIN_USER_TRACK",
            joinColumns = {@JoinColumn(name = "TRACK_ID", referencedColumnName = "TRACK_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")})
    private List<User> users;

    @OneToMany (
            targetEntity = Comment.class,
            mappedBy = "track",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Comment> comments;
}

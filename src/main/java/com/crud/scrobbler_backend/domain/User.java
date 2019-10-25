package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    public User(String username, String email, long spotifyId, List<Artist> artists, List<Track> tracks, List<Comment> comments) {
        this.username = username;
        this.email = email;
        this.spotifyId = spotifyId;
        this.artists = artists;
        this.tracks = tracks;
        this.comments = comments;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    private long id;

    @Setter
    @Column(name = "USERNAME")
    private String username;

    @Setter
    @Column(name = "EMAIL")
    private String email;

    @Setter
    @Column(name = "SPOTIFY_USER_ID")
    private long spotifyId;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Artist> artists = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "")
    private List<Track> tracks = new ArrayList<>();

    @OneToMany (
            targetEntity = Comment.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Comment> comments;
}
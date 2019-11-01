package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    public User(String username, String email, String spotifyId) {
        this.username = username;
        this.email = email;
        this.spotifyId = spotifyId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    protected long id;

    @Setter
    @Column(name = "USERNAME")
    private String username;

    @Setter
    @Column(name = "EMAIL")
    private String email;

    @Setter
    @Column(name = "SPOTIFY_USER_ID")
    private String spotifyId;

    @OneToMany(targetEntity = UsersArtist.class,
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<UsersArtist> usersArtists = new ArrayList<>();

    @OneToMany(
            targetEntity = UsersTrack.class,
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<UsersTrack> usersTracks = new ArrayList<>();

    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    private List<Comment> comments = new ArrayList<>();
}

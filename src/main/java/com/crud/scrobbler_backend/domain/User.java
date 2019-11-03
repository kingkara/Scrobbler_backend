package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
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
    @Column(name = "USERNAME", unique = true)
    private String username;

    @Setter
    @Column(name = "EMAIL")
    private String email;

    @Setter
    @Column(name = "SPOTIFY_USER_ID", unique = true)
    private String spotifyId;

    @OneToMany(mappedBy = "user")
    private List<UsersArtist> usersArtists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UsersTrack> usersTracks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
}

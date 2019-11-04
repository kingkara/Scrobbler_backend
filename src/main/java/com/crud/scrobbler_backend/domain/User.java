package com.crud.scrobbler_backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@userId")
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

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<UsersArtist> usersArtists = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<UsersTrack> usersTracks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
}

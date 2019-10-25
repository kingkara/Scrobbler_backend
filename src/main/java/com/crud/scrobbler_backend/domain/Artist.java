package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "ARTISTS")
public class Artist {

    public Artist(String name, long count, long spotifyId, List<User> users, List<Track> tracks) {
        this.name = name;
        this.count = count;
        this.spotifyId = spotifyId;
        this.users = users;
        this.tracks = tracks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTIST_ID", unique = true)
    private long artistId;

    @Setter
    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNT")
    private long count;

    @Column(name = "SPOTIFY_ID")
    private long spotifyId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_USER_ARTIST",
            joinColumns = {@JoinColumn(name = "ARTIST_ID", referencedColumnName = "ARTIST_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")})
    private List<User> users = new ArrayList<>();

    @OneToMany (
            targetEntity = Track.class,
            mappedBy = "artist",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Track> tracks = new ArrayList<>();
}

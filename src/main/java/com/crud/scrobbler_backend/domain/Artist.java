package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "ARTISTS")
public class Artist {

    public Artist(String name, String spotifyArtistId) {
        this.name = name;
        this.spotifyArtistId = spotifyArtistId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTIST_ID", unique = true)
    protected long artistId;

    @Setter
    @Column(name = "NAME", unique = true)
    private String name;

    @Setter
    @Column(name = "SPOTIFY_ARTIST_ID", unique = true)
    private String spotifyArtistId;

    @OneToMany(mappedBy = "artist")
    private List<UsersArtist> usersArtist = new ArrayList<>();

    @OneToMany(mappedBy = "artist")
    private List<Track> tracks = new ArrayList<>();
}

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "ARTISTS")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@artistId")
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

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "artist")
    private List<UsersArtist> usersArtist = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private List<Track> tracks = new ArrayList<>();
}

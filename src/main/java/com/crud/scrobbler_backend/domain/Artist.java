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

    public Artist(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTIST_ID", unique = true)
    private long artistId;

    @Setter
    @Column(name = "NAME")
    private String name;

    @OneToMany(targetEntity = UsersArtist.class,
            mappedBy = "artist",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<UsersArtist> usersArtist = new ArrayList<>();

    @OneToMany(
            targetEntity = Track.class,
            mappedBy = "artist",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Track> tracks = new ArrayList<>();
}

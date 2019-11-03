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
    @Column(name = "TITLE", unique = true)
    private String title;

    @Setter
    @ManyToOne
    @JoinColumn(name = "ARTIST_ID")
    private Artist artist;

    @OneToMany(mappedBy = "track")
    private List<UsersTrack> usersTracks = new ArrayList<>();

    @OneToMany(mappedBy = "track")
    private List<Comment> comments = new ArrayList<>();
}

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
@Table(name = "TRACKS")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@trackId")
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

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "track")
    private List<UsersTrack> usersTracks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "track")
    private List<Comment> comments = new ArrayList<>();
}

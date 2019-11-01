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
    @Column(name = "TITLE")
    private String title;

    @Setter
    @ManyToOne
    @JoinColumn(name = "ARTIST_ID")
    private Artist artist;

    @OneToMany (targetEntity = UsersTrack.class,
            mappedBy = "track",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<UsersTrack> usersTracks = new ArrayList<>();

    @OneToMany (
            targetEntity = Comment.class,
            mappedBy = "track",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    private List<Comment> comments = new ArrayList<>();
}

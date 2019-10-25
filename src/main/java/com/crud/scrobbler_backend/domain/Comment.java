package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment {

    public Comment(String text, User user, Track track) {
        this.text = text;
        this.user = user;
        this.track = track;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENTS", unique = true)
    private long id;

    @Setter
    @Column(name = "TEXT")
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRACK_ID")
    private Track track;
}

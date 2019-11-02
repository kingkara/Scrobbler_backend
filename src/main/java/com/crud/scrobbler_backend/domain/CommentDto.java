package com.crud.scrobbler_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id;
    private String text;
    private User user;
    private Track track;

    public CommentDto(String text, User user, Track track) {
        this.text = text;
        this.user = user;
        this.track = track;
    }
}

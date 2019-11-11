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
    private String username;
    private String trackTitle;

    public CommentDto(String text, String username, String trackTitle) {
        this.text = text;
        this.username = username;
        this.trackTitle = trackTitle;
    }
}

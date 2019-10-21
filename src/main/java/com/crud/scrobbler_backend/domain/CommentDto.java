package com.crud.scrobbler_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {
    private long id;
    private String text;
    private User user;
    private Track track;
}

package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Comment;
import com.crud.scrobbler_backend.domain.CommentDto;
import com.crud.scrobbler_backend.services.TracksService;
import com.crud.scrobbler_backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentsMapper {
    @Autowired
    private UsersService usersService;
    @Autowired
    private TracksService tracksService;

    public Comment mapToComment(final CommentDto commentDto) {
        return new Comment(commentDto.getText(), usersService.findByUsername(commentDto.getUsername()),
                tracksService.findByTitle(commentDto.getTrackTitle()));
    }

    public CommentDto mapToCommentDto(final Comment comment) {
        return new CommentDto(comment.getText(), comment.getUser().getUsername(),
                comment.getTrack().getTitle());
    }

    public List<CommentDto> mapToCommentDtoList(final List<Comment> comments) {
        return comments.stream()
                .map(c -> new CommentDto(c.getId(), c.getText(), c.getUser(), c.getTrack(), c.getUser().getUsername(),
                        c.getTrack().getTitle()))
                .collect(Collectors.toList());
    }
}

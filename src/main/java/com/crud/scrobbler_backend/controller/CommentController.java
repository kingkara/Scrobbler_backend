package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.CommentDto;
import com.crud.scrobbler_backend.exceptions.CommentNotFoundException;
import com.crud.scrobbler_backend.exceptions.TrackNotFoundException;
import com.crud.scrobbler_backend.mapper.CommentMapper;
import com.crud.scrobbler_backend.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class CommentController {

    @Autowired
    private CommentsService service;
    @Autowired
    private CommentMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/comments/{trackId}")
    public List<CommentDto> getComments(@PathVariable long trackId) throws TrackNotFoundException {
        return mapper.mapToCommentDtoList(service.getComments(trackId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/comments")
    public void createComment(@RequestBody CommentDto commentDto) {
        service.addComment(mapper.mapToComment(commentDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/comments")
    public CommentDto updateComment(@RequestBody CommentDto commentDto) throws CommentNotFoundException {
        return mapper.mapToCommentDto(service.editComment(mapper.mapToComment(commentDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/comments/{commentId}")
    public void deleteComment(@PathVariable long commentId) throws CommentNotFoundException {
        service.deleteComment(commentId);
    }
}

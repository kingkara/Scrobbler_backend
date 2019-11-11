package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.CommentDto;
import com.crud.scrobbler_backend.mapper.CommentsMapper;
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
    private CommentsMapper mapper;

    @GetMapping(value = "/comments/{trackId}")
    public List<CommentDto> getComments(@PathVariable long trackId) {
        return mapper.mapToCommentDtoList(service.getComments(trackId));
    }

    @PostMapping(value = "/comments")
    public void createComment(@RequestBody CommentDto commentDto) {
        service.addComment(mapper.mapToComment(commentDto));
    }

    @PutMapping(value = "/comments")
    public CommentDto updateComment(@RequestBody CommentDto commentDto) throws Exception {
        return mapper.mapToCommentDto(service.editComment(commentDto.getId(), commentDto.getText()));
    }

    @DeleteMapping(value = "/comments/{commentId}")
    public void deleteComment(@PathVariable long commentId) throws Exception {
        service.deleteComment(commentId);
    }
}

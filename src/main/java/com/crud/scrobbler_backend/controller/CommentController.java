package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.CommentDto;
import com.crud.scrobbler_backend.exceptions.CommentNotFoundException;
import com.crud.scrobbler_backend.exceptions.TrackNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/v1")
public class CommentController {

    @RequestMapping(method = RequestMethod.GET,value = "/comments")
    public List<CommentDto> getComments(@RequestParam long trackId) throws TrackNotFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/comments")
    public void createComment (@RequestBody String comment) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/comments")
    public CommentDto updateComment(@RequestBody CommentDto commentDto) {
        return new CommentDto();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/comments")
    public void deleteComment (@RequestParam long commentId) throws CommentNotFoundException {

    }
}

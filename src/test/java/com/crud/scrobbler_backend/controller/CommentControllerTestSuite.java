package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.*;
import com.crud.scrobbler_backend.mapper.CommentsMapper;
import com.crud.scrobbler_backend.services.CommentsService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
class CommentControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentsMapper mapper;
    @MockBean
    private CommentsService service;

    @Test
    void shouldGetComments() throws Exception {
        //Given
        User user = new User("Test name", "Test email", "Test spotify id");
        User user1 = new User("Test name 2", "Test email 2", "Test spotify id 2");
        Artist artist = new Artist("Test artist", "Test artist id");
        Track track = new Track("Test title", artist);
        long id = track.getId();

        CommentDto comment = new CommentDto("Test comment", user.getUsername(), track.getTitle());
        CommentDto comment1 = new CommentDto("Test comment 2", user1.getUsername(), track.getTitle());
        List<CommentDto> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment1);

        when(mapper.mapToCommentDtoList(service.getComments(id))).thenReturn(comments);

        //When & Then
        mockMvc.perform(get("/v1/comments/123").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("trackId", Long.toString(id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].text", is("Test comment")))
                .andExpect(jsonPath("$[0].username", is("Test name")))
                .andExpect(jsonPath("$[0].trackTitle", is("Test title")))
                .andExpect(jsonPath("$[1].text", is("Test comment 2")))
                .andExpect(jsonPath("$[1].username", is("Test name 2")))
                .andExpect(jsonPath("$[1].trackTitle", is("Test title")));
    }

    @Test
    void shouldCreateComment() throws Exception {
        //Given
        User user = new User("Test name", "Test email", "Test spotify id");
        Artist artist = new Artist("Test artist", "Test artist id");
        Track track = new Track("Test title", artist);

        Comment comment = new Comment("Test comment", user, track);
        CommentDto commentDto = new CommentDto("Test comment", user.getUsername(), track.getTitle());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(commentDto);

        when(service.addComment(mapper.mapToComment(ArgumentMatchers.any(CommentDto.class)))).thenReturn(comment);

        //When & Then
        mockMvc.perform(post("/v1/comments/").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(service).addComment(mapper.mapToComment(ArgumentMatchers.any(CommentDto.class)));
    }

    @Test
    void shouldUpdateComment() throws Exception {
        //Given
        User user = new User("Test name", "Test email", "Test spotify id");
        Artist artist = new Artist("Test artist", "Test artist id");
        Track track = new Track("Test title", artist);

        CommentDto commentDto = new CommentDto("Test comment", user.getUsername(), track.getTitle());
        CommentDto updatedComment = new CommentDto("Updated", user.getUsername(), track.getTitle());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(commentDto);

        when(mapper.mapToCommentDto(service.editComment(commentDto.getId(), updatedComment.getText()))).thenReturn(updatedComment);

        //When & Then
        mockMvc.perform(put("/v1/comments").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("text", is("Updated")));
    }

    @Test
    void shouldDeleteComment() throws Exception {
        //Given
        User user = new User("Test name", "Test email", "Test spotify id");
        Artist artist = new Artist("Test artist", "Test artist id");
        Track track = new Track("Test title", artist);

        Comment comment = new Comment("Test comment", user, track);
        CommentDto commentDto = new CommentDto(123L,"Test comment", user, track, user.getUsername(), track.getTitle());

        when(service.getComment(commentDto.getId())).thenReturn(comment);
        when(service.addComment(mapper.mapToComment(ArgumentMatchers.any(CommentDto.class)))).thenReturn(comment);

        //When & Then
        mockMvc.perform(delete("/v1/comments/123").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("commentId", Long.toString(commentDto.getId())))
                .andExpect(status().isOk());
        verify(service).deleteComment(commentDto.getId());
    }
}
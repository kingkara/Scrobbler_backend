package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Comment;
import com.crud.scrobbler_backend.domain.CommentDto;
import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommentsMapperTestSuite {
    @Autowired
    private CommentsMapper mapper;

    @Test
    void shouldMapToComment() {
        //Given
        CommentDto commentDto = new CommentDto(1, "test text", new User(), new Track());

        //When
        Comment comment = mapper.mapToComment(commentDto);

        //Then
        assertEquals("test text", comment.getText());
    }

    @Test
    void shouldMapToCommentDto() {
        //Given
        Comment comment = new Comment("test text", new User(), new Track());

        //When
        CommentDto commentDto = mapper.mapToCommentDto(comment);

        //Then
        assertEquals("test text", commentDto.getText());
    }

    @Test
    void shouldMapToCommentDtoList() {
        //Given
        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment("test text", new User(), new Track());
        Comment comment1 = new Comment("test text2", new User(), new Track());
        comments.add(comment);
        comments.add(comment1);

        //When
        List<CommentDto> commentDtos = mapper.mapToCommentDtoList(comments);

        //Then
        assertEquals(2, commentDtos.size());
        assertEquals("test text", commentDtos.get(0).getText());
        assertEquals("test text2", commentDtos.get(1).getText());
    }
}
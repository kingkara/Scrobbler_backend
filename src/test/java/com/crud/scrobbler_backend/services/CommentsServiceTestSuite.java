package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.Comment;
import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.exceptions.CommentNotFoundException;
import com.crud.scrobbler_backend.exceptions.TrackNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommentsServiceTestSuite {
    @Autowired
    private CommentsService service;
    @Autowired
    private TracksService tracksService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ArtistsService artistsService;

    @Test
    void shouldGetComments() throws TrackNotFoundException, CommentNotFoundException {
        //Given
        User user = new User("Test username", "Test email", "Test spotify id");
        usersService.saveUser(user);
        Artist artist = new Artist("Test name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        tracksService.addTrack(track);
        long trackId = track.getId();

        Comment comment = new Comment("Test comment", user, track);
        service.addComment(comment);
        long id = comment.getId();

        //When
        List<Comment> comments = service.getComments(trackId);
        int commentToCheckRow = comments.size() - 1;

        //Then
        assertEquals("Test comment", comments.get(commentToCheckRow).getText());
        assertEquals("Test username", comments.get(commentToCheckRow).getUser().getUsername());
        assertEquals("Test email", comments.get(commentToCheckRow).getUser().getEmail());
        assertEquals("Test spotify id", comments.get(commentToCheckRow).getUser().getSpotifyId());
        assertEquals("Test title", comments.get(commentToCheckRow).getTrack().getTitle());
        assertEquals("Test name", comments.get(commentToCheckRow).getTrack().getArtist().getName());
        assertEquals("Test id", comments.get(commentToCheckRow).getTrack().getArtist().getSpotifyArtistId());

        //CleanUp
        service.deleteComment(id);
    }

    @Test
    void shouldAddComment() throws CommentNotFoundException {
        //Given
        User user = new User("Test username", "Test email", "Test spotify id");
        usersService.saveUser(user);
        Artist artist = new Artist("Test name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        tracksService.addTrack(track);

        Comment comment = new Comment("Test comment", user, track);

        //When
        Comment commentAdded = service.addComment(comment);
        long id = commentAdded.getId();

        //Then
        assertEquals("Test comment", commentAdded.getText());
        assertEquals("Test username", commentAdded.getUser().getUsername());
        assertEquals("Test email", commentAdded.getUser().getEmail());
        assertEquals("Test spotify id", commentAdded.getUser().getSpotifyId());
        assertEquals("Test title", commentAdded.getTrack().getTitle());
        assertEquals("Test name", commentAdded.getTrack().getArtist().getName());
        assertEquals("Test id", commentAdded.getTrack().getArtist().getSpotifyArtistId());

        //CleanUp
        service.deleteComment(id);
    }

    @Test
    void shouldEditComment() throws CommentNotFoundException {
        //Given
        User user = new User("Test username", "Test email", "Test spotify id");
        usersService.saveUser(user);
        Artist artist = new Artist("Test name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        tracksService.addTrack(track);

        Comment comment = new Comment("Test comment", user, track);
        service.addComment(comment);
        long id = comment.getId();

        //When
        Comment updatedComment = service.editComment(id, "Update comment text");

        //Then
        assertEquals("Update comment text", updatedComment.getText());
        assertEquals("Test username", updatedComment.getUser().getUsername());
        assertEquals("Test email", updatedComment.getUser().getEmail());
        assertEquals("Test spotify id", updatedComment.getUser().getSpotifyId());
        assertEquals("Test title", updatedComment.getTrack().getTitle());
        assertEquals("Test name", updatedComment.getTrack().getArtist().getName());
        assertEquals("Test id", updatedComment.getTrack().getArtist().getSpotifyArtistId());

        //CleanUp
        service.deleteComment(id);
    }
}
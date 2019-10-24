package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.Comment;
import com.crud.scrobbler_backend.exceptions.CommentNotFoundException;
import com.crud.scrobbler_backend.exceptions.TrackNotFoundException;
import com.crud.scrobbler_backend.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {
    @Autowired
    private CommentsRepository repository;

    public List<Comment> getComments(final long trackId) throws TrackNotFoundException {
        return repository.findAllByTrack_Id(trackId);
    }

    public void addComment(final Comment comment) {
        repository.save(comment);
    }

    public Comment editComment(final Comment comment) throws CommentNotFoundException {
        Comment updatedComment = repository.findById(comment.getId()).orElseThrow(CommentNotFoundException::new);
        updatedComment.setText(comment.getText());
        return repository.save(updatedComment);
    }

    public void deleteComment(final long id) throws CommentNotFoundException {
        Comment commentToDelete = repository.findById(id).orElseThrow(CommentNotFoundException::new);
        repository.delete(commentToDelete);
    }
}

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

    public Comment getComment(final long id) throws CommentNotFoundException {
        return repository.findById(id).orElseThrow(CommentNotFoundException::new);
    }
    public List<Comment> getComments(final long trackId) throws TrackNotFoundException {
        return repository.findAllByTrack_Id(trackId);
    }

    public Comment addComment(final Comment comment) {
        return repository.save(comment);
    }

    public Comment editComment(final long comentId, final String updateCommentText) throws CommentNotFoundException {
        Comment updatedComment = repository.findById(comentId).orElseThrow(CommentNotFoundException::new);
        updatedComment.setText(updateCommentText);
        return repository.save(updatedComment);
    }

    public void deleteComment(final long id) throws CommentNotFoundException {
        Comment commentToDelete = repository.findById(id).orElseThrow(CommentNotFoundException::new);
        repository.delete(commentToDelete);
    }
}

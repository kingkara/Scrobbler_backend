package com.crud.scrobbler_backend.repository;

import com.crud.scrobbler_backend.domain.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CommentsRepository extends CrudRepository<Comment, Long> {
    @Override
    List<Comment> findAll();

    List<Comment> findByTrack_Id(long trackId);
}

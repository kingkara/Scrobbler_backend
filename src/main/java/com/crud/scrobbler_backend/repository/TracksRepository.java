package com.crud.scrobbler_backend.repository;

import com.crud.scrobbler_backend.domain.Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TracksRepository extends CrudRepository<Track, Long> {
    @Override
    List<Track> findAll();

    List<Track> findAllByUsers (long userId);

    List<Track> findAllByFavouriteStatusAndUsers (boolean status, long userId);

    List<Track> findAllByUsersOrderByCount (long userId);
}

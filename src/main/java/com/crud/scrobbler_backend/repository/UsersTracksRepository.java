package com.crud.scrobbler_backend.repository;

import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.domain.UsersTrack;
import com.crud.scrobbler_backend.exceptions.UsersTrackNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersTracksRepository extends CrudRepository<UsersTrack, Long> {
    List<UsersTrack> findAllByUser(long userId) throws UsersTrackNotFoundException;

    List<UsersTrack> findAllByUserOrderByCount(long userId) throws UsersTrackNotFoundException;

    List<UsersTrack> findAllByUserAndFavouriteStatus(long userId, boolean status) throws UsersTrackNotFoundException;
}

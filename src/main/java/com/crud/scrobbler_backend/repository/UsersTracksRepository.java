package com.crud.scrobbler_backend.repository;

import com.crud.scrobbler_backend.domain.UsersTrack;
import com.crud.scrobbler_backend.exceptions.UsersTrackNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersTracksRepository extends CrudRepository<UsersTrack, Long> {
    List<UsersTrack> findAllByUser_Id(long userId) throws UsersTrackNotFoundException;

    List<UsersTrack> findAllByUser_IdOrderByCountDesc(long userId) throws UsersTrackNotFoundException;

    List<UsersTrack> findAllByUser_IdAndFavouriteStatus(long userId, boolean status) throws UsersTrackNotFoundException;

    UsersTrack findUsersTrackByUser_IdAndTrack_Id(long userId, long trackId);

    UsersTrack findById(UsersTrack.UsersTrackIdBuilder id);

}

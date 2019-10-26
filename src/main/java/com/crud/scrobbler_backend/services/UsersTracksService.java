package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.UsersTrack;
import com.crud.scrobbler_backend.exceptions.UsersTrackNotFoundException;
import com.crud.scrobbler_backend.repository.UsersTracksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTracksService {
    @Autowired
    private UsersTracksRepository repository;

    public List<UsersTrack> getAllUsersTracks (final long userId) throws UsersTrackNotFoundException {
        return repository.findAllByUser(userId);
    }
    public List<UsersTrack> getTopTracks (final long userId) throws UsersTrackNotFoundException {
        return repository.findAllByUserOrderByCount(userId);
    }

    public UsersTrack changeFavouriteStatus(final long id) throws UsersTrackNotFoundException {
        UsersTrack trackToUpdate = repository.findById(id).orElseThrow(UsersTrackNotFoundException ::new);
        if(trackToUpdate.isFavouriteStatus()) {
            trackToUpdate.setFavouriteStatus(false);
        }
        trackToUpdate.setFavouriteStatus(true);
        return repository.save(trackToUpdate);
    }

    public List<UsersTrack> getFavourites(final long userId) throws UsersTrackNotFoundException {
        return repository.findAllByUserAndFavouriteStatus(userId, true);
    }
}

package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.exceptions.TrackNotFoundException;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import com.crud.scrobbler_backend.exceptions.UsersTrackNotFoundException;
import com.crud.scrobbler_backend.repository.TracksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TracksService {
    @Autowired
    private TracksRepository repository;

    public List<Track> findAllTracksFromUser (final long userId) throws UserNotFoundException {
        return repository.findAllByUsers(userId);
    }

    public void deleteTrack(final long id) throws UsersTrackNotFoundException {
        Track trackToDelete = repository.findById(id).orElseThrow(UsersTrackNotFoundException::new);
        repository.delete(trackToDelete);
    }

    public List<Track> getTopTracks (final long userId) throws UserNotFoundException {
        return repository.findAllByFavouriteStatusAndUsers(true, userId);
    }

    public Track changeFavouriteStatus(final long id) throws UsersTrackNotFoundException  {
        Track trackToUpdate = repository.findById(id).orElseThrow(UsersTrackNotFoundException ::new);
        if(trackToUpdate.isFavouriteStatus()) {
            trackToUpdate.setFavouriteStatus(false);
        }
        trackToUpdate.setFavouriteStatus(true);
        return repository.save(trackToUpdate);
    }

    public List<Track> getFavourites(final long userId) throws UserNotFoundException {
        return repository.findAllByUsersOrderByCount(userId);
    }
}

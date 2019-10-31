package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.UsersTrack;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
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
        return repository.findAllByUser_Id(userId);
    }

    public List<UsersTrack> getTopTracks (final long userId) throws UsersTrackNotFoundException {
        return repository.findAllByUser_IdOrderByCount(userId);
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
        return repository.findAllByUser_IdAndFavouriteStatus(userId, true);
    }

    public UsersTrack getByUserAndTrackId(final long userId, final long trackId) {
        return repository.findUsersTrackByUser_IdAndTrack_Id(userId, trackId);
    }

    public void addUsersTrack(final UsersTrack usersTrack) {
        repository.save(usersTrack);
    }

    public void updateCountAndLastlyPlayed(final long id, final String thisTimePlayed) throws UsersTrackNotFoundException {
        UsersTrack trackToUpdate = repository.findById(id).orElseThrow(UsersTrackNotFoundException ::new);
        String lastlyPlayedAt = trackToUpdate.getLastPlayedTime();
        if(!thisTimePlayed.equals(lastlyPlayedAt)) {
            trackToUpdate.setCount(trackToUpdate.getCount()+1);
            trackToUpdate.setLastPlayedTime(thisTimePlayed);
            repository.save(trackToUpdate);
        }
    }
}

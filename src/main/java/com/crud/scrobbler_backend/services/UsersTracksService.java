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

    public List<UsersTrack> getAllUsersTracks(final long userId) throws UsersTrackNotFoundException {
        return repository.findAllByUser_Id(userId);
    }

    public List<UsersTrack> getTopTracks(final long userId) throws UsersTrackNotFoundException {
        return repository.findAllByUser_IdOrderByCountDesc(userId).subList(0, 5);
    }

    public UsersTrack changeFavouriteStatus(final long userId, final long trackId) {
        UsersTrack trackToUpdate = repository.findByUser_IdAndTrack_Id(userId, trackId);
        if (trackToUpdate.isFavouriteStatus()) {
            trackToUpdate.setFavouriteStatus(false);
            return repository.save(trackToUpdate);
        }
        trackToUpdate.setFavouriteStatus(true);
        return repository.save(trackToUpdate);
    }

    public List<UsersTrack> getFavourites(final long userId) throws UsersTrackNotFoundException {
        return repository.findAllByUser_IdAndFavouriteStatus(userId, true);
    }

    public UsersTrack getByUserAndTrackId(final long userId, final long trackId) {
        return repository.findByUser_IdAndTrack_Id(userId, trackId);
    }

    public UsersTrack addUsersTrack(final UsersTrack usersTrack) {
        return repository.save(usersTrack);
    }

    public UsersTrack updateCountAndLastlyPlayed(final UsersTrack.UsersTrackIdBuilder id, final String thisTimePlayed) {
        UsersTrack trackToUpdate = repository.findById(id);
        String lastlyPlayedAt = trackToUpdate.getLastPlayedTime();
        if (!thisTimePlayed.equals(lastlyPlayedAt)) {
            trackToUpdate.setCount(trackToUpdate.getCount() + 1);
            trackToUpdate.setLastPlayedTime(thisTimePlayed);
            repository.save(trackToUpdate);
        }
        return trackToUpdate;
    }

    public void deleteUsersTrack(final long userId, long trackId) {
        UsersTrack usersTrackToDelete = repository.findByUser_IdAndTrack_Id(userId, trackId);
        repository.delete(usersTrackToDelete);
    }
}

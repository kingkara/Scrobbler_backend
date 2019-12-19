package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.UsersArtist;
import com.crud.scrobbler_backend.exceptions.UsersArtistNotFoundException;
import com.crud.scrobbler_backend.repository.UsersArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersArtistsService {
    @Autowired
    private UsersArtistRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersTracksService.class);

    public List<UsersArtist> getAllArtists(final long userId) throws UsersArtistNotFoundException {
        return repository.getAllByUser_Id(userId);
    }

    public List<UsersArtist> getTopArtists(final long userId) {
        try {
            return repository.getAllByUser_IdOrderByCountDesc(userId).subList(0, 5);
        } catch (Exception e) {
            LOGGER.warn("Users Artists Not Found.");
        }
       return new ArrayList<>();
    }

    public UsersArtist getByUserAndArtistsId(final long userId, final long artistId) {
        return repository.getUsersArtistByUserIdAndArtist_ArtistId(userId, artistId);
    }

    public UsersArtist addUsersArtist(final UsersArtist usersArtist) {
        return repository.save(usersArtist);
    }

    public void updateCount(final UsersArtist.UsersArtistIdBuilder id, final String thisTimePlayedAt) throws UsersArtistNotFoundException {
        UsersArtist artistToUpdate = repository.findById(id);
        String lastlyPlayed = artistToUpdate.getLastPlayedTime();
        if (!thisTimePlayedAt.equals(lastlyPlayed)) {
            artistToUpdate.setCount(artistToUpdate.getCount() + 1);
            artistToUpdate.setLastPlayedTime(thisTimePlayedAt);
            repository.save(artistToUpdate);
        }
    }

    public void deleteUsersArtist(final long artistId, final long userId) {
        UsersArtist usersArtist = repository.getUsersArtistByUserIdAndArtist_ArtistId(userId, artistId);
        repository.delete(usersArtist);
    }
}

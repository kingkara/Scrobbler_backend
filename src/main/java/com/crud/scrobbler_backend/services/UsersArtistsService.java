package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.UsersArtist;
import com.crud.scrobbler_backend.exceptions.UsersArtistNotFoundException;
import com.crud.scrobbler_backend.repository.UsersArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersArtistsService {
    @Autowired
    private UsersArtistRepository repository;

    public List<UsersArtist> getAllArtists(final long userId) throws UsersArtistNotFoundException {
        return repository.getAllByUser_Id(userId);
    }

    public List<UsersArtist> getTopArtists(final long userId) throws UsersArtistNotFoundException {
        return repository.getAllByUser_IdOrderByCount(userId);
    }

    public UsersArtist getByUserAndArtistsId(final long userId, final long artistId) {
        return repository.getUsersArtistByUserIdAndArtist_ArtistId(userId, artistId);
    }

    public void addUsersArtist(final UsersArtist usersArtist) {
        repository.save(usersArtist);
    }

    public void updateCount(final long id, final String thisTimePlayedAt) throws UsersArtistNotFoundException {
        UsersArtist artistToUpdate = repository.findById(id).orElseThrow(UsersArtistNotFoundException::new);
        String lastlyPlayed = artistToUpdate.getLastPlayedTime();
        if (!thisTimePlayedAt.equals(lastlyPlayed)) {
            artistToUpdate.setCount(artistToUpdate.getCount() + 1);
            artistToUpdate.setLastPlayedTime(thisTimePlayedAt);
            repository.save(artistToUpdate);
        }
    }
}

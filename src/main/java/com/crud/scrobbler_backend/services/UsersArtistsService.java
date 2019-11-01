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
        return repository.getAllByUser_IdOrderByCountDesc(userId).subList(0, 5);
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

    public void deleteUsersArtist(final UsersArtist.UsersArtistIdBuilder id) throws UsersArtistNotFoundException {
        UsersArtist usersArtist = repository.findById(id);
        repository.delete(usersArtist);
    }
}

package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.exceptions.UsersArtistNotFoundException;
import com.crud.scrobbler_backend.repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistsService  {
    @Autowired
    private ArtistsRepository repository;

    public Artist getArtistById (final long id) throws UsersArtistNotFoundException {
        return repository.findById(id).orElseThrow(UsersArtistNotFoundException::new);
    }

    public void deleteArtist (final long id) throws UsersArtistNotFoundException {
        Artist artistToDelete = repository.findById(id).orElseThrow(UsersArtistNotFoundException::new);
        repository.delete(artistToDelete);
    }

    public List<Artist> getArtists (final long userId) {
        return repository.findAllByUsers(userId);
    }

    public List<Artist> getTopArtists (final long userId) {
        return repository.findAllByUsersOrderByCount(userId);
    }
}

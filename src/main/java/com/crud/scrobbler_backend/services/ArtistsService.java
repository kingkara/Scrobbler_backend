package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.exceptions.ArtistNotFoundException;
import com.crud.scrobbler_backend.repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistsService {
    @Autowired
    private ArtistsRepository repository;

    public Artist getArtistById(final long id) throws ArtistNotFoundException {
        return repository.findById(id).orElseThrow(ArtistNotFoundException::new);
    }

    public Artist getArtistByName(final String name) {
        return repository.findArtistByName(name);
    }

    public List<Artist> getArtists() {
        return repository.findAll();
    }

    public void deleteArtist(final long id) throws ArtistNotFoundException {
        Artist artistToDelete = repository.findById(id).orElseThrow(ArtistNotFoundException::new);
        repository.delete(artistToDelete);
    }

    public Artist addArtist(final Artist artist) {
        return repository.save(artist);
    }
}

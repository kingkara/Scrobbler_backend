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

    public List<Artist> getArtists() {
        return repository.findAll();
    }

    public void deleteArtist(final long id) throws ArtistNotFoundException {
        Artist artistToDelete = repository.findById(id).orElseThrow(ArtistNotFoundException::new);
        repository.delete(artistToDelete);
    }

    public List<Artist> getMostPlayedArtists() {
        return repository.findAllOrderByCount();
    }

    public void addArtist(final Artist artist) {
        repository.save(artist);
    }

    public Artist editArtist(final Artist artist) throws ArtistNotFoundException{
        Artist artistToUpdate = repository.findById(artist.getArtistId()).orElseThrow(ArtistNotFoundException::new);
        artistToUpdate.setName(artist.getName());
        return repository.save(artistToUpdate);
    }
}

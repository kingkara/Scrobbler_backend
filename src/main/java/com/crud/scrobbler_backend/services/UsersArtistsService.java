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

    public List<UsersArtist> getAllArtists(long userId) throws UsersArtistNotFoundException {
        return repository.getAllByUser(userId);
    }

    public List<UsersArtist> getTopArtists(long userId) throws UsersArtistNotFoundException {
        return repository.getAllByUserOrderByCount(userId);
    }

    public UsersArtist getArtist(long artistId) throws UsersArtistNotFoundException{
        return repository.getByArtist(artistId);
    }
}

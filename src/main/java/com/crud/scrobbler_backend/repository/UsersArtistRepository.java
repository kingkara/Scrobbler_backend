package com.crud.scrobbler_backend.repository;

import com.crud.scrobbler_backend.domain.UsersArtist;
import com.crud.scrobbler_backend.exceptions.UsersArtistNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersArtistRepository extends CrudRepository<UsersArtist, Long> {
    List<UsersArtist> getAllByUserOrderByCount(long userId) throws UsersArtistNotFoundException;

    List<UsersArtist> getAllByUser(long userId) throws UsersArtistNotFoundException;

    UsersArtist getByArtist(long artistId) throws UsersArtistNotFoundException;
}

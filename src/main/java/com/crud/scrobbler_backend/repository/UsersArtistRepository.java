package com.crud.scrobbler_backend.repository;

import com.crud.scrobbler_backend.domain.UsersArtist;
import com.crud.scrobbler_backend.exceptions.UsersArtistNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersArtistRepository extends CrudRepository<UsersArtist, Long> {
    List<UsersArtist> getAllByUser_IdOrderByCountDesc(long userId) throws UsersArtistNotFoundException;

    List<UsersArtist> getAllByUser_Id(long userId) throws UsersArtistNotFoundException;

    UsersArtist findById(UsersArtist.UsersArtistIdBuilder id) throws UsersArtistNotFoundException;

    UsersArtist getByArtist_ArtistId(long artistId) throws UsersArtistNotFoundException;

    UsersArtist getUsersArtistByUserIdAndArtist_ArtistId(long userId, long artistId);
}

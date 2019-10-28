package com.crud.scrobbler_backend.repository;

import com.crud.scrobbler_backend.domain.SpotifyTrack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotifyRepository extends CrudRepository<SpotifyTrack, Long> {
    @Override
    List<SpotifyTrack> findAll();
}

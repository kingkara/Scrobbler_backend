package com.crud.scrobbler_backend.repository;

import com.crud.scrobbler_backend.domain.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ArtistsRepository extends CrudRepository<Artist, Long> {
    @Override
    List<Artist> findAll();
    List<Artist> findAllOrderByCount();
}

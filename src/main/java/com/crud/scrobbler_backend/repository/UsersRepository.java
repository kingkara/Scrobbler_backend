package com.crud.scrobbler_backend.repository;

import com.crud.scrobbler_backend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
}

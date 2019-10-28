package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import com.crud.scrobbler_backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository repository;

    public User getUser(final long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public void saveUser(final User user) {
        repository.save(user);
    }

    public User changeUser(final User user) throws UserNotFoundException {
        User userToUpdate = repository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setSpotifyId(user.getSpotifyId());
        return repository.save(userToUpdate);
    }

    public void deleteUser(final long id) throws UserNotFoundException {
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        repository.delete(user);
    }
}

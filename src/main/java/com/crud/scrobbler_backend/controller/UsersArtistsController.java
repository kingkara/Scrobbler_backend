package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.ArtistDto;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import com.crud.scrobbler_backend.exceptions.UsersArtistNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1")
public class UsersArtistsController {

    @RequestMapping(method = RequestMethod.GET, value = "/usersArtists")
    public List<ArtistDto> getUsersArtists(@RequestParam long userId) throws UserNotFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/usersArtists")
    public void deleteUsersArtist(@RequestParam long usersArtistsId) throws UsersArtistNotFoundException {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersArtists")
    public List<ArtistDto> getTopFive(@RequestParam long userId) throws UserNotFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersArtists")
    public ArtistDto getArtist(@RequestParam long artistsId) {
        return new ArtistDto();
    }
}

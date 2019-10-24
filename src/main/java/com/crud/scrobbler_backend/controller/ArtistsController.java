package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.ArtistDto;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import com.crud.scrobbler_backend.exceptions.UsersArtistNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class ArtistsController {

    @RequestMapping(method = RequestMethod.GET, value = "/usersArtists/{userId}")
    public List<ArtistDto> getUsersArtists(@PathVariable long userId) throws UserNotFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/usersArtists/{userId}")
    public void deleteUsersArtist(@PathVariable long usersArtistsId) throws UsersArtistNotFoundException {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersTopArtists/{userId}")
    public List<ArtistDto> getTopFive(@PathVariable long userId) throws UserNotFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersArtists//{artistId}")
    public ArtistDto getArtist(@PathVariable long artistsId) {
        return new ArtistDto();
    }
}

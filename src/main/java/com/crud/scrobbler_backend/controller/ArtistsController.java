package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.ArtistDto;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import com.crud.scrobbler_backend.exceptions.UsersArtistNotFoundException;
import com.crud.scrobbler_backend.mapper.ArtistMapper;
import com.crud.scrobbler_backend.services.ArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class ArtistsController {

    @Autowired
    private ArtistsService service;
    @Autowired
    private ArtistMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/usersArtists/{userId}")
    public List<ArtistDto> getUsersArtists(@PathVariable long userId) throws UserNotFoundException {
        return mapper.mapToArtistDtoList(service.getArtists(userId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/usersArtists/{userId}")
    public void deleteUsersArtist(@PathVariable long usersArtistsId) throws UsersArtistNotFoundException {
        service.deleteArtist(usersArtistsId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersTopArtists/{userId}")
    public List<ArtistDto> getTopFive(@PathVariable long userId) throws UserNotFoundException {
        return mapper.mapToArtistDtoList(service.getTopArtists(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersArtists//{artistId}")
    public ArtistDto getArtist(@PathVariable long artistsId) throws UsersArtistNotFoundException {
        return mapper.mapToArtistDto(service.getArtistById(artistsId));
    }
}

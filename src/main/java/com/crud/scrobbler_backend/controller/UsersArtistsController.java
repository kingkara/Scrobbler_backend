package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.UsersArtistDto;
import com.crud.scrobbler_backend.exceptions.UsersArtistNotFoundException;
import com.crud.scrobbler_backend.mapper.UsersArtistsMapper;
import com.crud.scrobbler_backend.services.UsersArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class UsersArtistsController {
    @Autowired
    private UsersArtistsService service;
    @Autowired
    private UsersArtistsMapper mapper;

//    @RequestMapping(method = RequestMethod.GET, value = "/usersArtists/{artistId}")
//    public UsersArtistDto getArtist(@PathVariable long artistId) throws UsersArtistNotFoundException {
//        return mapper.mapToUsersArtistDto(service.getArtist(artistId));
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersArtists/{userId}")
    public List<UsersArtistDto> getUsersArtists(@PathVariable long userId) throws UsersArtistNotFoundException {
        return mapper.mapToUsersArtistDtoList(service.getAllArtists(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersArtists")
    public List<UsersArtistDto> getTopFive(@RequestBody long userId) throws UsersArtistNotFoundException {
        return mapper.mapToUsersArtistDtoList(service.getTopArtists(userId));
    }
}

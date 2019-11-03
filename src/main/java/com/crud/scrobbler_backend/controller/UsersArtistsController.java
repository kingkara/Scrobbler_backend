package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.UsersArtistDto;
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

    @GetMapping(value = "/usersArtists/{userId}")
    public List<UsersArtistDto> getUsersArtists(@PathVariable long userId) throws Exception {
        return mapper.mapToUsersArtistDtoList(service.getAllArtists(userId));
    }

    @GetMapping(value = "/usersArtists")
    public List<UsersArtistDto> getTopFive(@RequestBody long userId) throws Exception {
        return mapper.mapToUsersArtistDtoList(service.getTopArtists(userId));
    }

    @DeleteMapping(value = "/usersArtists/{artistId}/{userId}")
    public void deleteArtist(@PathVariable long artistId, @PathVariable long userId) {
        service.deleteUsersArtist(userId, artistId);
    }
}

package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.UsersTrackDto;
import com.crud.scrobbler_backend.exceptions.UsersTrackNotFoundException;
import com.crud.scrobbler_backend.mapper.UsersTracksMapper;
import com.crud.scrobbler_backend.services.UsersTracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class UsersTrackController {
    @Autowired
    private UsersTracksService service;
    @Autowired
    private UsersTracksMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/usersTracks/{userId}")
    public List<UsersTrackDto> getUsersTracks(@PathVariable long userId) throws UsersTrackNotFoundException {
        return mapper.mapToUsersTrackDtoList(service.getAllUsersTracks(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersTracks")
    public List<UsersTrackDto> getFavouriteTracks(@RequestBody long userId) throws UsersTrackNotFoundException {
        return mapper.mapToUsersTrackDtoList(service.getFavourites(userId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/usersTracks/{usersTrackId}")
    public UsersTrackDto changeFavouriteStatus(@PathVariable long usersTrackId) throws UsersTrackNotFoundException {
        return mapper.mapToUsersTrackDto(service.changeFavouriteStatus(usersTrackId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersTrack/top/{userId}")
    public List<UsersTrackDto> getTopUsersTracks(@PathVariable long userId) throws UsersTrackNotFoundException {
        return mapper.mapToUsersTrackDtoList(service.getTopTracks(userId));
    }
}

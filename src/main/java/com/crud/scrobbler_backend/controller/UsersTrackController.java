package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.UsersTrackDto;
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

    @GetMapping(value = "/usersTracks/{userId}")
    public List<UsersTrackDto> getUsersTracks(@PathVariable long userId) throws Exception {
        return mapper.mapToUsersTrackDtoList(service.getAllUsersTracks(userId));
    }

    @GetMapping(value = "/usersTracks/fav/{userId}")
    public List<UsersTrackDto> getFavouriteTracks(@PathVariable long userId) throws Exception {
        return mapper.mapToUsersTrackDtoList(service.getFavourites(userId));
    }

    @PutMapping(value = "/usersTracks/{userId}/{trackId}")
    public UsersTrackDto changeFavouriteStatus(@PathVariable long userId, @PathVariable long trackId) {
        return mapper.mapToUsersTrackDto(service.changeFavouriteStatus(userId, trackId));
    }

    @GetMapping(value = "/usersTracks/top/{userId}")
    public List<UsersTrackDto> getTopUsersTracks(@PathVariable long userId) throws Exception {
        return mapper.mapToUsersTrackDtoList(service.getTopTracks(userId));
    }

    @DeleteMapping(value = "/usersTracks/{userId}/{trackId}")
    public void deleteTrack(@PathVariable long userId, @PathVariable long trackId) {
        service.deleteUsersTrack(userId, trackId);
    }
}

package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.TrackDto;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import com.crud.scrobbler_backend.exceptions.UsersTrackNotFoundException;
import com.crud.scrobbler_backend.mapper.TrackMapper;
import com.crud.scrobbler_backend.services.TracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class TracksController {

    @Autowired
    private TracksService service;
    @Autowired
    private TrackMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/usersTracks/{userId}")
    public List<TrackDto> getUsersTracks(@PathVariable long userId) throws UserNotFoundException {
        return mapper.mapToTrackDtoList(service.findAllTracksFromUser(userId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/usersTracks/{usersTrackId}")
    public void deleteTrack(@PathVariable long usersTrackId) throws UsersTrackNotFoundException {
        service.deleteTrack(usersTrackId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersFavTracks/{userId}")
    public List<TrackDto> getFavouriteTracks(@PathVariable long userId) throws UserNotFoundException {
        return mapper.mapToTrackDtoList(service.getFavourites(userId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/usersTracks/{usersTrackId}")
    public TrackDto changeFavouriteStatus(@PathVariable long usersTrackId) throws UsersTrackNotFoundException {
        return mapper.mapToTrackDto(service.changeFavouriteStatus(usersTrackId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersTopTracks/{userId}")
    public List<TrackDto> getTopFive(@PathVariable long userId) throws UserNotFoundException {
        return mapper.mapToTrackDtoList(service.getTopTracks(userId));
    }
}

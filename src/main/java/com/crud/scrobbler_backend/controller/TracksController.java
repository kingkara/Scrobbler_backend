package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.TrackDto;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import com.crud.scrobbler_backend.exceptions.UsersTrackNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class TracksController {

    @RequestMapping(method = RequestMethod.GET, value = "/usersTracks/{userId}")
    public List<TrackDto> getUsersTracks(@PathVariable long userId) throws UserNotFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/usersTracks/{usersTrackId}")
    public void deleteTrack(@PathVariable long usersTrackId) throws UsersTrackNotFoundException {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersFavTracks/{userId}")
    public List<TrackDto> getFavouriteTracks(@PathVariable long userId) throws UserNotFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/usersTracks/{usersTrackId}")
    public TrackDto changeFavouriteStatus (@PathVariable long usersTrackId) throws UsersTrackNotFoundException {
        return new TrackDto();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersTopTracks/{userId}")
    public List<TrackDto> getTopFive(@PathVariable long userId) throws UserNotFoundException {
        return new ArrayList<>();
    }
}

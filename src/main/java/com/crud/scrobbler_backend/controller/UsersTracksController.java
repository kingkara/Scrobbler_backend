package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.UsersTrack;
import com.crud.scrobbler_backend.domain.UsersTrackDto;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import com.crud.scrobbler_backend.exceptions.UsersTrackNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class UsersTracksController {

    @RequestMapping(method = RequestMethod.GET, value = "/usersTracks")
    public List<UsersTrackDto> getUsersTracks(@RequestParam long userId) throws UserNotFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/usersTracks")
    public void deleteTrack(@RequestParam long usersTrackId) throws UsersTrackNotFoundException {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersTracks")
    public List<UsersTrackDto> getFavouriteTracks(@RequestParam long userId) throws UserNotFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/usersTracks")
    public UsersTrackDto changeFavouriteStatus (@RequestParam long usersTrackId) throws UsersTrackNotFoundException {
        return new UsersTrackDto();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersTracks")
    public List<UsersTrackDto> getTopFive(@RequestParam long userId) throws UserNotFoundException {
        return new ArrayList<>();
    }
}

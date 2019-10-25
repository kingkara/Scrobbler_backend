package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.SpotifyTrackDto;
import com.crud.scrobbler_backend.exceptions.SpotifyTokenNotProvidedFoundException;
import com.crud.scrobbler_backend.services.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/spotify")
public class SpotifyController {
    @Autowired
    private SpotifyService service;

    @RequestMapping(method = RequestMethod.GET, value = "/playback")
    public List<SpotifyTrackDto> getPlayback() throws SpotifyTokenNotProvidedFoundException {
        return service.getPlayback();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/current")
    public SpotifyTrackDto getCurrentPlaying() throws SpotifyTokenNotProvidedFoundException {
        return service.getCurrentPlaying();
    }

}

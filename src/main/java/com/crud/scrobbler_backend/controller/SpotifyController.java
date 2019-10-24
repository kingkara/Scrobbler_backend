package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.SpotifyTrackDto;
import com.crud.scrobbler_backend.exceptions.SpotifyTokenNotProvidedFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/spotify")
public class SpotifyController {

    @RequestMapping(method = RequestMethod.GET, value = "/playback")
    public List<SpotifyTrackDto> getPlayback() throws SpotifyTokenNotProvidedFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/current")
    public SpotifyTrackDto getCurrentPlaying() throws SpotifyTokenNotProvidedFoundException {
        return new SpotifyTrackDto();
    }

}

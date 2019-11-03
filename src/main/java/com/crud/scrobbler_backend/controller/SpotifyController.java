package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.spotify.SpotifyCurrentlyPlayedDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrackDto;
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

    @GetMapping(value = "/playback")
    public List<SpotifyFullTrackDto> getPlayback() throws Exception {
        return service.getPlayback();
    }

    @GetMapping(value = "/current")
    public SpotifyCurrentlyPlayedDto getCurrentPlaying() throws Exception {
        return service.getCurrentPlaying();
    }

}

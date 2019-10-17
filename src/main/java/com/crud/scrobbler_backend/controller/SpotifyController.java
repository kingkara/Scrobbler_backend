package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.SpotifyPlaybackDto;
import com.crud.scrobbler_backend.domain.SpotifyTrack;
import com.crud.scrobbler_backend.exceptions.SpotifyUserNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/spotify")
public class SpotifyController {

    @RequestMapping(method = RequestMethod.GET, value = "/playback")
    public SpotifyPlaybackDto getPlayback(@RequestParam long spotifyUsersId) throws SpotifyUserNotFoundException {
        return new SpotifyPlaybackDto();
    }

    public SpotifyTrack getCurrentPlaying(@RequestParam long spotifyUsersId) throws SpotifyUserNotFoundException {
        return new SpotifyTrack();
    }
}

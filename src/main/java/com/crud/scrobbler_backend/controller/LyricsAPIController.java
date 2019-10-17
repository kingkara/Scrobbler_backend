package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.ArtistBioDto;
import com.crud.scrobbler_backend.domain.LyricsDto;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/SongMeanings")
public class LyricsAPIController {
    @RequestMapping(method = RequestMethod.GET, value = "/lyrics")
    public LyricsDto getLyrics(@RequestParam long spotifyTrackId) {
        return new LyricsDto();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/artistBio")
    public ArtistBioDto getArtistBio (@RequestParam long artistId) {
        return new ArtistBioDto();
    }
}

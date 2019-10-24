package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.LyricsDto;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/SongMeanings")
public class LyricsAPIController {
    @RequestMapping(method = RequestMethod.GET, value = "/lyrics/{artistName}/{title}")
    public LyricsDto getLyrics(@PathVariable String artistName, @PathVariable String title) {
        return new LyricsDto();
    }
}

package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.LyricsDto;
import com.crud.scrobbler_backend.mapper.LyricsApiMapper;
import com.crud.scrobbler_backend.services.LyricsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/SongMeanings")
public class LyricsAPIController {
    @Autowired
    private LyricsApiService service;
    @Autowired
    private LyricsApiMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/lyrics/{artistName}/{title}")
    public LyricsDto getLyrics(@PathVariable String artistName, @PathVariable String title) {
        return service.getLyrics(artistName, title);
    }
}

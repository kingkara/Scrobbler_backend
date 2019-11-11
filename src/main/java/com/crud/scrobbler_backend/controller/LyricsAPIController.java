package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.lyricsApi.LyricsDto;
import com.crud.scrobbler_backend.services.LyricsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class LyricsAPIController {
    @Autowired
    private LyricsApiService service;

    @GetMapping(value = "/lyrics/{artistName}/{title}")
    public LyricsDto getLyrics(@PathVariable String artistName, @PathVariable String title) throws Exception {
        return service.getLyrics(artistName, title);
    }
}

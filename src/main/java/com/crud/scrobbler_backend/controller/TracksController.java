package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.TrackDto;
import com.crud.scrobbler_backend.mapper.TracksMapper;
import com.crud.scrobbler_backend.services.TracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class TracksController {
    @Autowired
    private TracksService service;
    @Autowired
    private TracksMapper mapper;

    @GetMapping(value = "/tracks/{trackTitle}")
    public TrackDto getTrack(@PathVariable String trackTitle) {
        return mapper.mapToTrackDto(service.findByTitle(trackTitle));
    }

    @GetMapping(value = "/tracks")
    public List<TrackDto> getTracks() {
        return mapper.mapToTrackDtoList(service.getTracks());
    }

    @GetMapping(value = "/tracks/artist/{artistName}")
    public List<TrackDto> getTrackByArtistName(@PathVariable String artistName) {
        return mapper.mapToTrackDtoList(service.getTracksByArtistName(artistName));
    }
}

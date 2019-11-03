package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.TrackDto;
import com.crud.scrobbler_backend.mapper.TracksMapper;
import com.crud.scrobbler_backend.services.TracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class TracksController {

    @Autowired
    private TracksService service;
    @Autowired
    private TracksMapper mapper;

    @GetMapping(value = "/tracks/{trackId}")
    public TrackDto getTrack(@PathVariable long trackId) throws Exception {
        return mapper.mapToTrackDto(service.getTrack(trackId));
    }
}

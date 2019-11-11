package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.ArtistDto;
import com.crud.scrobbler_backend.mapper.ArtistMapper;
import com.crud.scrobbler_backend.services.ArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class ArtistsController {

    @Autowired
    private ArtistsService service;
    @Autowired
    private ArtistMapper mapper;

    @GetMapping(value = "/artists/{artistName}")
    public ArtistDto getArtist(@PathVariable String artistName) {
        return mapper.mapToArtistDto(service.getArtistByName(artistName));
    }

    @GetMapping(value = "/artists")
    public List<ArtistDto> getArtists() {
        return mapper.mapToArtistDtoList(service.getArtists());
    }
}

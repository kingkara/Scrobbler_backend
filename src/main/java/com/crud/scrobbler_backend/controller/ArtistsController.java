package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.ArtistDto;
import com.crud.scrobbler_backend.exceptions.ArtistNotFoundException;
import com.crud.scrobbler_backend.exceptions.UserNotFoundException;
import com.crud.scrobbler_backend.exceptions.UsersArtistNotFoundException;
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

    @RequestMapping(method = RequestMethod.GET, value = "/artists/{artistId}")
    public ArtistDto getArtist(@PathVariable long artistId) throws ArtistNotFoundException {
        return mapper.mapToArtistDto(service.getArtistById(artistId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/artists/{artistId}")
    public void deleteArtist(@PathVariable long artistsId) throws ArtistNotFoundException {
        service.deleteArtist(artistsId);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/artists")
//    public List<ArtistDto> getTopFive() {
//        return mapper.mapToArtistDtoList(service.getMostPlayedArtists());
//    }

}

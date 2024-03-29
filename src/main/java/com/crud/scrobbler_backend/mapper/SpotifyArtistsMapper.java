package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.spotify.SpotifyArtist;
import com.crud.scrobbler_backend.domain.spotify.SpotifyArtistDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpotifyArtistsMapper {

    public List<SpotifyArtist> mapToSpotifyArtistList(final List<SpotifyArtistDto> spotifyArtistDtoList) {
        return spotifyArtistDtoList.stream()
                .map(spotifyArtistDto -> new SpotifyArtist(spotifyArtistDto.getId(), spotifyArtistDto.getName()))
                .collect(Collectors.toList());
    }
}

package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.SpotifyArtist;
import com.crud.scrobbler_backend.domain.SpotifyArtistDto;
import org.springframework.stereotype.Component;

@Component
public class SpotifyArtistsMapper {
    public SpotifyArtist mapToSpotifyArtist (final SpotifyArtistDto spotifyArtistDto) {
        return new SpotifyArtist(spotifyArtistDto.getId(), spotifyArtistDto.getName());
    }

    public SpotifyArtistDto mapToSpotifyArtistDto(final SpotifyArtist spotifyArtist) {
        return new SpotifyArtistDto(spotifyArtist.getId(), spotifyArtist.getName());
    }
}

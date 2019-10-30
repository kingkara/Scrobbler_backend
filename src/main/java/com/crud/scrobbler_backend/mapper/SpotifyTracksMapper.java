package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrack;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpotifyTracksMapper {
    @Autowired
    SpotifyArtistsMapper artistsMapper;

    public SpotifyFullTrack mapToSpotifyTrack (final SpotifyFullTrackDto spotifyFullTrackDto) {
        return new SpotifyFullTrack(spotifyFullTrackDto.getSpotifyTrackDto(), spotifyFullTrackDto.getPlayedAt());
    }


    public SpotifyFullTrackDto mapToSpotifyTrackDto (final SpotifyFullTrack spotifyFullTrack) {
        return new SpotifyFullTrackDto(spotifyFullTrack.getSpotifyTrackDto(), spotifyFullTrack.getPlayedAt());
    }

    public List<SpotifyFullTrack> mapToSpotifyTrackList (final List<SpotifyFullTrackDto> spotifyFullTrackDtos) {
        return spotifyFullTrackDtos.stream()
                .map(s->new SpotifyFullTrack(s.getSpotifyTrackDto(), s.getPlayedAt()))
                .collect(Collectors.toList());
    }
}
